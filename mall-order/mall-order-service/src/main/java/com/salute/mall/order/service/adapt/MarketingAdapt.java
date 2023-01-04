package com.salute.mall.order.service.adapt;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.marketing.api.client.MarketingApiClient;
import com.salute.mall.marketing.api.request.ReturnCouponRequest;
import com.salute.mall.marketing.api.request.SubmitOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.SubmitOrderResponse;
import com.salute.mall.order.service.adapt.converter.MarketingAdaptConverter;
import com.salute.mall.order.service.adapt.dto.SubmitOrderAdaptDTO;
import com.salute.mall.order.service.adapt.dto.SubmitOrderDTO;
import com.salute.mall.order.service.adapt.dto.UseCouponAdaptDTO;
import com.salute.mall.order.service.pojo.dto.CancelOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class MarketingAdapt {

    @Autowired
    private MarketingApiClient marketingApiClient;

    @Autowired
    private MarketingAdaptConverter marketingAdaptConverter;

    /**
     * @Description 使用优惠券
     * @author liuhu
     * @param useCouponAdaptDTO
     * @date 2023/1/4 10:15
     * @return void
     */
    public void useCoupon(UseCouponAdaptDTO useCouponAdaptDTO) {
        UseCouponRequest request = marketingAdaptConverter.convertToUseCouponRequest(useCouponAdaptDTO);
        log.info("execute useCoupon info,req:{}", JSON.toJSONString(request));
        Result<Void> result = marketingApiClient.useCoupon(request);
        log.info("execute useCoupon success,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(result));
        if(Objects.isNull(result) || !Objects.equals(result.isSuccess(),Boolean.TRUE)){
            log.error("execute useCoupon error,req:{},resp:{}",JSON.toJSONString(request), JSON.toJSONString(result));
            throw new BusinessException("500","扣减优惠券异常");
        }
    }

    /**
     * @Description 提交优惠计算分摊
     * @author liuhu
     * @param submitOrderAdaptDTO
     * @date 2023/1/4 10:19
     * @return void
     */
    public SubmitOrderDTO submitOrder(SubmitOrderAdaptDTO submitOrderAdaptDTO){
        SubmitOrderRequest request = marketingAdaptConverter.convertToSubmitOrderRequest(submitOrderAdaptDTO);
        log.info("execute submitOrder info,req:{}", JSON.toJSONString(request));
        Result<SubmitOrderResponse> result = marketingApiClient.submitOrder(request);
        log.info("execute submitOrder info,req:{}，resp：{}",JSON.toJSONString(request),JSON.toJSONString(result));
        if(Objects.isNull(result) || !Objects.equals(result.isSuccess(),Boolean.TRUE) || Objects.isNull(result.getResult())){
            throw new BusinessException("500","下单提交优惠计算分摊异常");
        }
        return marketingAdaptConverter.convertToSubmitOrderDTO(result.getResult());
    }

    /**
     * @Description 归还优惠券
     * @author liuhu
     * @param cancelOrderDTO
     * @date 2023/1/3 17:22
     * @return void
     */
    public void rollbackCoupon(CancelOrderDTO cancelOrderDTO) {
        ReturnCouponRequest request = new ReturnCouponRequest();
        request.setBizCode(cancelOrderDTO.getSaleOrderCode());
        request.setOperator("");
        request.setOperateCode("");
        try {
            log.error("execute returnCoupon info,req:{}", JSON.toJSONString(request));
            Result<Void> result = marketingApiClient.returnCoupon(request);
            log.error("execute returnCoupon success,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(result));
            if(Objects.isNull(result) || !Objects.equals(result.isSuccess(),Boolean.TRUE)){
                log.error("execute returnCoupon error,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(result));
            }
        } catch (Exception e) {
            log.error("execute returnCoupon error,req:{}", JSON.toJSONString(request), e);
        }
    }
}
