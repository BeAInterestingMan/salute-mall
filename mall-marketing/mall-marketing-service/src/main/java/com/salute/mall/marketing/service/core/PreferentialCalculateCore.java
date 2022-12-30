package com.salute.mall.marketing.service.core;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.marketing.service.enums.PreferentialTypeEnum;
import com.salute.mall.marketing.service.pojo.context.OrderContext;
import com.salute.mall.marketing.service.pojo.context.OrderDetailContext;
import com.salute.mall.marketing.service.pojo.dto.AvailableCouponDetailInfoDTO;
import com.salute.mall.marketing.service.pojo.dto.AvailableCouponDiscountInfoDTO;
import com.salute.mall.marketing.service.pojo.dto.AvailableCouponInfoDTO;
import com.salute.mall.marketing.service.pojo.dto.ProductDiscountPreferentialDTO;
import com.salute.mall.marketing.service.pojo.dto.discount.SubmitOrderResultDTO;
import com.salute.mall.marketing.service.pojo.dto.discount.SubmitOrderSkuResultDTO;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord;
import com.salute.mall.marketing.service.pojo.entity.MarketingPreferentialRecord;
import com.salute.mall.marketing.service.repository.MarketingPreferentialRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  @Description 营销优惠计算核心实现
 *  @author liuhu
 *  @Date 2022/12/30 16:10
 */
@Component
@Slf4j
public class PreferentialCalculateCore {

    @Autowired
    private MarketingPreferentialRecordRepository marketingPreferentialRecordRepository;


    /**
     * @Description 计算分摊
     * @author liuhu
     * @param orderContext
     * @date 2022/12/16 17:40
     * @return com.salute.mall.marketing.service.pojo.dto.discount.SubmitOrderResultDTO
     */
    public SubmitOrderResultDTO calculationApportionment(AvailableCouponInfoDTO couponInfoDTO,
                                                         OrderContext orderContext) {
        //1.获取当前优惠券在订单维度的优惠信息
        List<AvailableCouponDiscountInfoDTO> discountInfoDTOS = queryCouponInstanceDiscountInOrderContext(Lists.newArrayList(couponInfoDTO), orderContext.getOrderOriginAmount());
        AvailableCouponDiscountInfoDTO discountInfoDTO = discountInfoDTOS.get(0);
        //2.获得商品维度的优惠分摊信息
        List<ProductDiscountPreferentialDTO> discountPreferentialDTOS = calculationProductDiscountPreferentialDTOList(couponInfoDTO, discountInfoDTO);
        //3.保存分摊记录
        saveMarketingPreferentialRecord(orderContext,discountPreferentialDTOS);
        //4.返回分摊信息
        return buildSubmitOrderResultDTO(discountInfoDTO,discountPreferentialDTOS,orderContext);
    }

    private SubmitOrderResultDTO buildSubmitOrderResultDTO(AvailableCouponDiscountInfoDTO couponDiscountInfoDTO,
                                                           List<ProductDiscountPreferentialDTO> discountPreferentialDTOS,
                                                           OrderContext orderContext) {
        Map<String, ProductDiscountPreferentialDTO> productDiscountPreferentialDTOMap = discountPreferentialDTOS.stream().collect(Collectors.toMap(ProductDiscountPreferentialDTO::getSkuCode, Function.identity(), (k1, k2) -> k1));
        SubmitOrderResultDTO submitOrderResultDTO = new SubmitOrderResultDTO();
        submitOrderResultDTO.setOrderOriginAmount(orderContext.getOrderOriginAmount());
        submitOrderResultDTO.setCouponPreferentialAmount(couponDiscountInfoDTO.getCouponPreferentialAmount());
        submitOrderResultDTO.setOrderFinalAmount(couponDiscountInfoDTO.getOrderFinalAmount());
        List<SubmitOrderSkuResultDTO> skuResultDTOS = orderContext.getOrderDetailContextList().stream().map(orderDetail -> {
            ProductDiscountPreferentialDTO dto = productDiscountPreferentialDTOMap.get(orderDetail.getSkuCode());
            SubmitOrderSkuResultDTO skuResultDTO = new SubmitOrderSkuResultDTO();
            skuResultDTO.setSkuCode(orderDetail.getSkuCode());
            skuResultDTO.setSalePrice(orderDetail.getSalePrice());
            skuResultDTO.setBugQty(orderDetail.getBuyQty());
            skuResultDTO.setSkuName(orderDetail.getSkuName());
            if (Objects.isNull(dto)) {
                skuResultDTO.setProductOriginAmount(BigDecimal.ZERO);
                skuResultDTO.setProductPreferentialAmount(BigDecimal.ZERO);
                skuResultDTO.setProductFinalAmount(BigDecimal.ZERO);
            } else {
                skuResultDTO.setProductOriginAmount(dto.getProductOriginAmount());
                skuResultDTO.setProductPreferentialAmount(dto.getProductPreferentialAmount());
                skuResultDTO.setProductFinalAmount(skuResultDTO.getProductOriginAmount().subtract(dto.getProductPreferentialAmount()));
            }
            return skuResultDTO;
        }).collect(Collectors.toList());
        submitOrderResultDTO.setSkuPreferentialList(skuResultDTOS);
        return submitOrderResultDTO;
    }


    private void saveMarketingPreferentialRecord(OrderContext orderContext,
                                                 List<ProductDiscountPreferentialDTO> discountPreferentialDTOS) {
        Map<String, OrderDetailContext> detailContextMap = orderContext.getOrderDetailContextList().stream().collect(Collectors.toMap(OrderDetailContext::getSkuCode, Function.identity(), (k1, k2) -> k1));
        List<MarketingPreferentialRecord> preferentialRecordList = discountPreferentialDTOS.stream().map(preferentialDTO -> {
            OrderDetailContext detailContext = detailContextMap.get(preferentialDTO.getSkuCode());
            SaluteAssertUtil.isTrue(Objects.nonNull(detailContext), "当前商品信息不存在，skuCode：{}", preferentialDTO.getSkuCode());
            MarketingPreferentialRecord record = new MarketingPreferentialRecord();
            record.setPreferentialCode(preferentialDTO.getCouponCode());
            record.setPreferentialType(PreferentialTypeEnum.COUPON.getDesc());
            record.setPreferentialName(preferentialDTO.getCouponName());
            record.setPreferentialRecordCode(IdUtil.getSnowflake().nextIdStr());
            record.setSkuCode(detailContext.getSkuCode());
            record.setSalePrice(detailContext.getSalePrice());
            record.setTotalOriginAmount(preferentialDTO.getProductOriginAmount());
            record.setTotalPreferentialAmount(preferentialDTO.getProductPreferentialAmount());
            record.setTotalPreferentialAfterAmount(record.getTotalOriginAmount().subtract(record.getTotalPreferentialAmount()));
            record.setCreatedTime(new Date());
            record.setBuyQty(detailContext.getBuyQty());
            return record;
        }).collect(Collectors.toList());
        marketingPreferentialRecordRepository.batchInsert(preferentialRecordList);
    }

    /**
     * @Description 计算优惠券的优惠金额
     * @author liuhu
     * @param availableCouponInfoDTOS
     * @date 2022/12/10 13:06
     * @return java.util.List<com.salute.mall.marketing.service.pojo.dto.AvailableCouponDiscountInfoDTO>
     */
    public List<AvailableCouponDiscountInfoDTO> queryCouponInstanceDiscountInOrderContext(List<AvailableCouponInfoDTO> availableCouponInfoDTOS,
                                                                                          BigDecimal orderOriginTotalAmount){
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(availableCouponInfoDTOS) && Objects.nonNull(orderOriginTotalAmount),"参数异常");
        //1.订单原始总金额
        return  availableCouponInfoDTOS.stream().map(infoDTO -> {
            AvailableCouponDiscountInfoDTO dto = new AvailableCouponDiscountInfoDTO();
            //2.可用优惠券的商品满足总金额
            BigDecimal productTotalAmount = infoDTO.getAvailableSkuList().stream().map(v -> new BigDecimal(v.getBuyCount()).multiply(v.getSalePrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
            //3.如果可优惠的商品金额大于优惠券的面值 则优惠金额未优惠券的面值 否则商品金额
            BigDecimal orderDiscountTotalAmount = productTotalAmount.compareTo(infoDTO.getCouponAmount())>0?infoDTO.getCouponAmount():productTotalAmount;
            dto.setCouponCode(infoDTO.getCouponCode());
            dto.setCouponAmount(infoDTO.getCouponAmount());
            dto.setCouponPreferentialAmount(orderDiscountTotalAmount);
            dto.setOrderAmount(orderDiscountTotalAmount);
            dto.setOrderFinalAmount(orderOriginTotalAmount.subtract(orderDiscountTotalAmount));
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * @Description 获得参与优惠券商品的优惠分摊
     * @author liuhu
     * @param couponInfoDTO
     * @param discountInfoDTO
     * @date 2022/12/16 16:17
     * @return java.util.List<com.salute.mall.marketing.service.pojo.dto.ProductDiscountPreferentialDTO>
     */
    private List<ProductDiscountPreferentialDTO> calculationProductDiscountPreferentialDTOList(AvailableCouponInfoDTO couponInfoDTO,
                                                                                               AvailableCouponDiscountInfoDTO discountInfoDTO) {
        List<ProductDiscountPreferentialDTO> preferentialDTOList = new ArrayList<>();
        BigDecimal couponDiscountAmount = discountInfoDTO.getCouponPreferentialAmount();
        //已经优惠的金额
        BigDecimal discountAmount = new BigDecimal(0);
        //商品的优惠金额
        BigDecimal productDiscountAmount =new BigDecimal(0);
        for (int i = 0; i < couponInfoDTO.getAvailableSkuList().size(); i++) {
            AvailableCouponDetailInfoDTO infoDTO = couponInfoDTO.getAvailableSkuList().get(i);
            BigDecimal productAmount = new BigDecimal(infoDTO.getBuyCount()).multiply(infoDTO.getSalePrice());
            productDiscountAmount = productAmount.divide(couponDiscountAmount, 2, BigDecimal.ROUND_DOWN).multiply(couponDiscountAmount);
            discountAmount = discountAmount.add(productDiscountAmount);
            // 最后一位 减法兜底
            if(i == couponInfoDTO.getAvailableSkuList().size()-1){
                productDiscountAmount = couponDiscountAmount.subtract(discountAmount);
            }
            //商品分摊金额已经大于商品的总金额了
            // 类似 商品A 1分钱，商品B 5分钱 优惠6分钱。按比例计算后 商品A分摊为 0分，商品B分摊为6分
            if(productDiscountAmount.compareTo(productAmount)>0){
                productDiscountAmount = productAmount;
            }
            ProductDiscountPreferentialDTO dto =  buildProductDiscountPreferentialDTO(infoDTO,productDiscountAmount,productAmount);
            preferentialDTOList.add(dto);
        }
        // 2.修正数据 上一步会导致 优惠总金额和所有商品累计优惠金额对不上
        fixProductDiscountPreferentialDTOList(preferentialDTOList,couponDiscountAmount);
        // 3.校验金额正确信息
        checkPreferentialAvailable(preferentialDTOList,discountInfoDTO);
        return preferentialDTOList;
    }

    /**
     * @Description 分摊金额校验
     * @author liuhu
     * @param preferentialDTOList
     * @param discountInfoDTO
     * @date 2022/12/16 17:15
     * @return void
     */
    private void checkPreferentialAvailable(List<ProductDiscountPreferentialDTO> preferentialDTOList,AvailableCouponDiscountInfoDTO discountInfoDTO) {
        for (ProductDiscountPreferentialDTO dto : preferentialDTOList) {
            SaluteAssertUtil.isTrue(dto.getProductOriginAmount().compareTo(dto.getProductPreferentialAmount()) >= 0, "商品优惠总金额应该小于等于商品总金额");
        }
        BigDecimal totalPreferentialAmount = preferentialDTOList.stream().map(ProductDiscountPreferentialDTO::getProductPreferentialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        SaluteAssertUtil.isTrue(discountInfoDTO.getCouponPreferentialAmount().compareTo(totalPreferentialAmount) == 0, "优惠券总优惠金额应不等于商品分摊金额合计");
        SaluteAssertUtil.isTrue(discountInfoDTO.getCouponAmount().compareTo(totalPreferentialAmount) >= 0, "优惠券金额应大于等于总优惠分摊金额");
    }

    /**
     * @Description 修正异常情况金额
     * @author liuhu
     * @param preferentialDTOList
     * @param couponDiscountAmount
     * @date 2022/12/16 17:10
     * @return void
     */
    private void fixProductDiscountPreferentialDTOList(List<ProductDiscountPreferentialDTO> preferentialDTOList,
                                                       BigDecimal couponDiscountAmount) {
        BigDecimal alreadyDiscountAmount = preferentialDTOList.stream().map(ProductDiscountPreferentialDTO::getProductPreferentialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        //未正确分摊需要修复的优惠金额
        BigDecimal needFixDiscountAmount = couponDiscountAmount.subtract(alreadyDiscountAmount);
        if (needFixDiscountAmount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        //商品A 1分钱，商品B 5分钱 优惠6分钱 商品A分摊为 0分，商品B分摊为6分
        for (ProductDiscountPreferentialDTO dto : preferentialDTOList) {
            SaluteAssertUtil.isTrue(dto.getProductOriginAmount().compareTo(dto.getProductPreferentialAmount()) > 0, "商品优惠总金额大于商品总金额");
            if (dto.getProductOriginAmount().compareTo(dto.getProductPreferentialAmount()) < 0) {
                boolean canFix = needFixDiscountAmount.compareTo(dto.getProductOriginAmount()) >= 0;
                if (canFix) {
                    dto.setProductPreferentialAmount(dto.getProductOriginAmount());
                    needFixDiscountAmount = needFixDiscountAmount.subtract(dto.getProductOriginAmount());
                }
            }
            SaluteAssertUtil.isTrue(needFixDiscountAmount.compareTo(BigDecimal.ZERO) >= 0, "计算金额异常");
        }

    }

    /**
     * @Description 获取商品优惠分摊
     * @author liuhu
     * @param infoDTO
     * @param productDiscountAmount
     * @param totalAmount
     * @date 2022/12/16 16:29
     * @return com.salute.mall.marketing.service.pojo.dto.ProductDiscountPreferentialDTO
     */
    private ProductDiscountPreferentialDTO buildProductDiscountPreferentialDTO(AvailableCouponDetailInfoDTO infoDTO,
                                                                               BigDecimal productDiscountAmount,
                                                                               BigDecimal totalAmount) {
        ProductDiscountPreferentialDTO dto = new ProductDiscountPreferentialDTO();
        dto.setCouponCode(dto.getCouponCode());
        dto.setSkuCode(infoDTO.getSkuCode());
        dto.setSalePrice(infoDTO.getSalePrice());
        dto.setBuyCount(infoDTO.getBuyCount());
        dto.setProductPreferentialAmount(productDiscountAmount);
        dto.setProductOriginAmount(totalAmount);
        return dto;
    }
}
