package com.salute.mall.order.service.core;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.order.service.adapt.MarketingAdapt;
import com.salute.mall.order.service.adapt.ProductStockAdapt;
import com.salute.mall.order.service.adapt.dto.OperateFreezeStockAdaptDTO;
import com.salute.mall.order.service.adapt.dto.OperateFreezeStockSkuAdaptDTO;
import com.salute.mall.order.service.enums.PayStatusEnum;
import com.salute.mall.order.service.enums.SaleOrderStatusEnum;
import com.salute.mall.order.service.helper.MessageSendHelper;
import com.salute.mall.order.service.pojo.dto.BusinessOperationLogDTO;
import com.salute.mall.order.service.pojo.dto.CancelOrderDTO;
import com.salute.mall.order.service.pojo.entity.SaleOrder;
import com.salute.mall.order.service.pojo.entity.SaleOrderDetail;
import com.salute.mall.order.service.pojo.po.CancelOrderPO;
import com.salute.mall.order.service.repository.SaleOrderDetailRepository;
import com.salute.mall.order.service.repository.SaleOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CancelSaleOrderCore {

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Autowired
    private MarketingAdapt marketingAdapt;

    @Autowired
    private SaleOrderDetailRepository saleOrderDetailRepository;

    @Autowired
    private ProductStockAdapt productStockAdapt;

    @Autowired
    private MessageSendHelper messageSendHelper;

    /**
     *  @Description 超时取消订单
     *  @author liuhu
     *  @Date 2023/1/3 10:38
     */
    public void delayCancel(CancelOrderDTO cancelOrderDTO) {
       SaluteAssertUtil.isTrue(Objects.nonNull(cancelOrderDTO) && StringUtils.isNotBlank(cancelOrderDTO.getSaleOrderCode()),"参数异常");
       doCancelSaleOrder(cancelOrderDTO);
    }

    /**
     * @Description 核心取消订单
     * @author liuhu
     * @param cancelOrderDTO
     * @date 2023/1/3 11:13
     * @return void
     */
    private void doCancelSaleOrder(CancelOrderDTO cancelOrderDTO) {
        SaleOrder saleOrder = saleOrderRepository.getBySaleOrderCode(cancelOrderDTO.getSaleOrderCode());
        //1.业务校验
        checkCancelAvailable(saleOrder);
        //2.更新订单状态为已取消
        cancelSaleOrderData(cancelOrderDTO);
        //3.归还优惠券
        marketingAdapt.rollbackCoupon(cancelOrderDTO);
        //4.归还库存
        returnProductStock(cancelOrderDTO);
        //5.归还积分

        //6.记录日志
        doRecordCancelSaleOrderLog(cancelOrderDTO);
    }

    private void doRecordCancelSaleOrderLog(CancelOrderDTO cancelOrderDTO) {
        BusinessOperationLogDTO dto = new BusinessOperationLogDTO();
        dto.setBizCode(cancelOrderDTO.getSaleOrderCode());
        dto.setAppId("MALL_ORDER");
        dto.setScene("SALE_ORDER");
        dto.setLogDesc("取消订单"+cancelOrderDTO.getOperator());
        dto.setOperator(cancelOrderDTO.getOperator());
        dto.setOperateCode(cancelOrderDTO.getOperateCode());
        messageSendHelper.sendSync("businessOperationLogCreateTopic",null, JSON.toJSONString(dto));
    }

    private void returnProductStock(CancelOrderDTO cancelOrderDTO) {
        List<SaleOrderDetail> saleOrderDetails = saleOrderDetailRepository.queryBySaleOrderCode(cancelOrderDTO.getSaleOrderCode());
        OperateFreezeStockAdaptDTO dto = new OperateFreezeStockAdaptDTO();
        dto.setOperateType("");
        dto.setBizCode(cancelOrderDTO.getSaleOrderCode());
        List<OperateFreezeStockSkuAdaptDTO> adaptDTOS = saleOrderDetails.stream().map(saleOrderDetail -> {
            OperateFreezeStockSkuAdaptDTO stockSkuAdaptDTO = new OperateFreezeStockSkuAdaptDTO();
            stockSkuAdaptDTO.setStockNum(saleOrderDetail.getBuyQty());
            stockSkuAdaptDTO.setProductCode(saleOrderDetail.getProductCode());
            stockSkuAdaptDTO.setSkuCode(saleOrderDetail.getSkuCode());
            stockSkuAdaptDTO.setSkuName(saleOrderDetail.getSkuName());
            stockSkuAdaptDTO.setProductCode(saleOrderDetail.getProductCode());
            return stockSkuAdaptDTO;
        }).collect(Collectors.toList());
        dto.setSkuStockList(adaptDTOS);
        productStockAdapt.operateFreezeStock(dto);
    }


    private void cancelSaleOrderData(CancelOrderDTO cancelOrderDTO) {
        CancelOrderPO orderPO = CancelOrderPO.builder()
                .cancelType(cancelOrderDTO.getCancelType())
                .reason(cancelOrderDTO.getReason())
                .operateCode(cancelOrderDTO.getOperateCode())
                .operator(cancelOrderDTO.getOperator())
                .build();
        saleOrderRepository.cancelOrder(orderPO);
    }

    private void checkCancelAvailable(SaleOrder saleOrder) {
        if (Objects.isNull(saleOrder)) {
            log.info("当前订单不存在");
            throw new BusinessException("500", "当前订单不存在");
        }
        if (!Objects.equals(saleOrder.getStatus(), SaleOrderStatusEnum.WAIT_AUDIT.getValue())) {
            log.info("当前订单不是待审核，saleOrderCode:{}", saleOrder.getSaleOrderCode());
            throw new BusinessException("500", "当前订单不是待审核");
        }
        if (!Objects.equals(saleOrder.getPayStatus(), PayStatusEnum.PAY_SUCCESS.getValue())) {
            log.info("当前订单已支付成功，saleOrderCode:{}", saleOrder.getSaleOrderCode());
            throw new BusinessException("500", "当前订单已支付成功");
        }
    }
}
