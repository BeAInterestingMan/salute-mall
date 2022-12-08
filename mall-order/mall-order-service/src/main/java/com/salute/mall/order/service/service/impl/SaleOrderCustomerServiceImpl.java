package com.salute.mall.order.service.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.order.service.enums.SaleOrderStatusEnum;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderDTO;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderProductSkuDTO;
import com.salute.mall.order.service.pojo.entity.SaleOrder;
import com.salute.mall.order.service.pojo.entity.SaleOrderDetail;
import com.salute.mall.order.service.repository.SaleOrderDetailRepository;
import com.salute.mall.order.service.repository.SaleOrderRepository;
import com.salute.mall.order.service.service.SaleOrderCustomerService;
import com.salute.mall.product.api.client.ProductApiClient;
import com.salute.mall.product.api.client.ProductStockApiClient;
import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import com.salute.mall.product.api.request.OperateFreezeStockSkuRequest;
import com.salute.mall.product.api.response.ProductSkuResponse;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SaleOrderCustomerServiceImpl implements SaleOrderCustomerService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ProductApiClient productApiClient;

    @Autowired
    private ProductStockApiClient productStockApiClient;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private SaleOrderDetailRepository saleOrderDetailRepository;

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Override
    public String getSaleOrderCode() {
        return IdUtil.getSnowflake().nextIdStr();
    }

    @Override
    public void createSaleOrder(CreateSaleOrderDTO dto) {
        String key  = RedisConstants.LockKey.SHOPPING_CREATE_SALE_ORDER+dto.getSaleOrderCode();
        RLock lock = redissonClient.getLock(key);
        try {
            //500ms尝试获取锁  获取锁后2s内自动释放锁
            boolean lockFlag = lock.tryLock(500, 2000, TimeUnit.MILLISECONDS);
            if(!lockFlag){
                throw new BusinessException("500","手速太快了，请稍后操作");
            }
            doCreateSaleOrder(dto);
        } catch (InterruptedException e) {
            log.error("创建订单获取锁失败,req:{}", JSON.toJSONString(dto));
            throw new BusinessException("500","创建订单获取锁异常");
        }catch (Exception e){
            log.error("创建订单获取锁失败,req:{}", JSON.toJSONString(dto));
            throw new BusinessException("500","创建订单异常");
        }finally {
            // 判断当前线程已加锁 如果把trylock放在try外面则不需要判断  redission自动通过线程ID判断是否时本线程释放锁
            if(lock.isLocked()&& lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    private void doCreateSaleOrder(CreateSaleOrderDTO dto) {
        List<String> skuCodeList = dto.getProductSkuList().stream().map(CreateSaleOrderProductSkuDTO::getSkuCode).collect(Collectors.toList());
        Result<List<ProductSkuResponse>> result = productApiClient.queryProductSkuList(skuCodeList);
        List<ProductSkuResponse> skuResponses = result.getResult();
        //1.业务校验
        validOrderBusinessAvailable(dto,skuResponses);
        try {
            //2.使用优惠券
            usedCoupon(dto);
            //3.扣库存
            operateStock(dto);
            //4.创建订单
            saveSaleOrder(dto,skuResponses);
        } catch (Exception e) {
            // TODO 丢消息 异步归还
        }
    }

    private void operateStock(CreateSaleOrderDTO dto){
        OperateFreezeStockRequest request = buildOperateFreezeStockRequest(dto);
        Result<Void> result = productStockApiClient.operateFreezeStock(request);
        if(Objects.isNull(result) || !Objects.equals(result.isStatus(),Boolean.TRUE)){
            throw new BusinessException("500","扣减优惠券异常");
        }
    }

    private void saveSaleOrder(CreateSaleOrderDTO dto,List<ProductSkuResponse> skuResponses) {
        List<SaleOrderDetail> saleOrderDetails = buildInsertSaleOrderDetail(dto, skuResponses);
        SaleOrder saleOrder = buildInsertSaleOrder(dto, saleOrderDetails);
        transactionTemplate.execute(ts->{
            try {
                saleOrderDetailRepository.insertBatch(saleOrderDetails);
                saleOrderRepository.insert(saleOrder);
            } catch (Exception e) {
                ts.setRollbackOnly();
                log.error("创建订单异常，req:{}",JSON.toJSONString(dto),e);
                throw new BusinessException("500","创建订单异常");
            }
            return null;
        });
    }

    private OperateFreezeStockRequest buildOperateFreezeStockRequest(CreateSaleOrderDTO dto) {
        OperateFreezeStockRequest request = new OperateFreezeStockRequest();
        request.setOperateCode("");
        request.setOperator("");
        request.setBizCode(dto.getSaleOrderCode());
        List<OperateFreezeStockSkuRequest> skuRequests = dto.getProductSkuList().stream().map(productSkuDTO -> {
            OperateFreezeStockSkuRequest stockSkuRequest = new OperateFreezeStockSkuRequest();
            stockSkuRequest.setSkuName(productSkuDTO.getSkuName());
            stockSkuRequest.setSkuCode(productSkuDTO.getSkuCode());
            stockSkuRequest.setStockNum(productSkuDTO.getBuyQty());
            stockSkuRequest.setProductCode(productSkuDTO.getProductCode());
            return stockSkuRequest;
        }).collect(Collectors.toList());
        request.setSkuStockList(skuRequests);
        return request;
    }

    private SaleOrder buildInsertSaleOrder(CreateSaleOrderDTO dto, List<SaleOrderDetail> saleOrderDetails) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setSaleOrderCode(dto.getSaleOrderCode());
        saleOrder.setStatus(SaleOrderStatusEnum.WAIT_AUDIT.getValue());
        saleOrder.setSource(dto.getSource());
        saleOrder.setOrderRemark(dto.getOrderRemark());
        BigDecimal totalSaleAmount = saleOrderDetails.stream().map(SaleOrderDetail::getSalePrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        saleOrder.setTotalSaleAmount(totalSaleAmount);
        saleOrder.setTotalPayableAmount(dto.getPayableAmount());
        BigDecimal totalCostAmount = saleOrderDetails.stream().map(SaleOrderDetail::getCouponAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        saleOrder.setTotalCostAmount(totalCostAmount);
        BigDecimal totalMarketingAmount = saleOrderDetails.stream().map(SaleOrderDetail::getMarketingPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        saleOrder.setTotalMarketingAmount(totalMarketingAmount);
        return saleOrder;
    }


    private List<SaleOrderDetail> buildInsertSaleOrderDetail(CreateSaleOrderDTO dto,
                                                             List<ProductSkuResponse> skuResponses) {
        Map<String, ProductSkuResponse> skuResponseMap = skuResponses.stream().collect(Collectors.toMap(ProductSkuResponse::getSkuCode, Function.identity(), (k1, k2) -> k1));
        return  dto.getProductSkuList().stream().map(productSku->{
            ProductSkuResponse skuResponse = skuResponseMap.get(productSku.getSkuCode());
            if(Objects.isNull(skuResponse)){
                log.error("skuCode获取商品信息为空，skuCode:{}",productSku.getSkuCode());
                throw new BusinessException("500","商品不存在");
            }
            SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
            saleOrderDetail.setSaleOrderCode(dto.getSaleOrderCode());
            saleOrderDetail.setBuyQty(productSku.getBuyQty());
            saleOrderDetail.setCostPrice(skuResponse.getCostPrice());
            saleOrderDetail.setMarketingPrice(skuResponse.getMarketPrice());
            saleOrderDetail.setMainImage("");
            saleOrderDetail.setSkuCode(skuResponse.getSkuCode());
            saleOrderDetail.setSkuName(skuResponse.getSkuName());
            return saleOrderDetail;
        }).collect(Collectors.toList());
    }

    private void usedCoupon(CreateSaleOrderDTO dto) {

    }

    private void validOrderBusinessAvailable(CreateSaleOrderDTO dto,List<ProductSkuResponse> skuResponses) {
        //1,校验商品信息是否正确
        validProductAvailable(dto.getProductSkuList(),skuResponses);
        //2.校验营销是否正确
        validMarketAvailable(dto,skuResponses);
        //3.校验订单金额是否正确
        validOrderAvailable(dto,skuResponses);
    }

    private void validMarketAvailable(CreateSaleOrderDTO dto, List<ProductSkuResponse> skuResponses) {
    }

    private void validOrderAvailable(CreateSaleOrderDTO dto, List<ProductSkuResponse> skuResponses) {
        BigDecimal totalProductAmount = skuResponses.stream().map(ProductSkuResponse::getSalePrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        if(totalProductAmount.compareTo(dto.getOrderAmount()) != 0){
            log.error("订单金额异常,订单总金额不等于商品总金额，orderInfo:{}",JSON.toJSONString(dto));
            throw new BusinessException("500","订单金额异常，请刷新购物车重新下单");
        }
        if(Objects.equals(dto.getCouponFlag(),Boolean.FALSE)){
            // 未使用营销 则订单总金额=应付金额=商品总金额
           if(totalProductAmount.compareTo(dto.getPayableAmount()) !=0){
               log.error("订单金额异常，订单应付金额不等于商品总金额，orderInfo:{}",JSON.toJSONString(dto));
               throw new BusinessException("500","订单金额异常，请刷新购物车重新下单");
           }
        }
        if(Objects.equals(dto.getCouponFlag(),Boolean.TRUE)){
            // 使用营销  应付金额= 商品总金额 - 优惠券金额
            boolean flag = dto.getCouponAmount().add(dto.getPayableAmount()).compareTo(totalProductAmount) == 0;
            if(flag){
                log.error("订单金额异常，订单应付金额计算有误，orderInfo:{}",JSON.toJSONString(dto));
                throw new BusinessException("500","订单金额异常，请刷新购物车重新下单");
            }
        }
    }

    private void validProductAvailable(List<CreateSaleOrderProductSkuDTO> productSkuList, List<ProductSkuResponse> skuResponses) {
         Map<String, ProductSkuResponse> skuResponseMap = skuResponses.stream().collect(Collectors.toMap(ProductSkuResponse::getSkuCode, Function.identity(), (k1, k2) -> k1));
         for (CreateSaleOrderProductSkuDTO dto : productSkuList) {
             ProductSkuResponse skuResponse = skuResponseMap.get(dto.getSkuCode());
             if(Objects.isNull(skuResponse)){
                 log.error("skuCode获取商品信息为空，skuCode:{}",dto.getSkuCode());
                 throw new BusinessException("500","商品不存在");
             }
             if(Objects.equals("DOWN",skuResponse.getStatus())){
                 log.error("skuCode商品已失效，skuCode:{}",dto.getSkuCode());
                 throw new BusinessException("500",skuResponse.getSkuName()+"商品已失效，请刷新购物车重新下单");
             }
             if(dto.getSalePrice().compareTo(skuResponse.getSalePrice()) != 0){
                 log.error("skuCode价格已发生变更，skuCode:{}",dto.getSkuCode());
                 throw new BusinessException("500",skuResponse.getSkuName()+"商品已失效，请刷新购物车重新下单");
             }
        }
    }
}
