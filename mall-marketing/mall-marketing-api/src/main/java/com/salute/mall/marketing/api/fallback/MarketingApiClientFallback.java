package com.salute.mall.marketing.api.fallback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.marketing.api.client.MarketingApiClient;
import com.salute.mall.marketing.api.request.PrepareOrderRequest;
import com.salute.mall.marketing.api.request.SubmitOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.PrepareOrderResponse;
import com.salute.mall.marketing.api.response.SubmitOrderResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@Slf4j
public class MarketingApiClientFallback implements FallbackFactory<MarketingApiClient> {

    @Override
    public MarketingApiClient create(Throwable throwable) {
        return new MarketingApiClient() {
            @Override
            public Result<Void> useCoupon(@Valid UseCouponRequest request) {
                log.error("execute ProductApiClient useCoupon error,request:{}", JSON.toJSONString(request),throwable);
                return Result.error("500","服务繁忙");
            }

            @Override
            public Result<PrepareOrderResponse> prepareOrder(@Valid PrepareOrderRequest request) {
                log.error("execute ProductApiClient prepareOrder error,request:{}", JSON.toJSONString(request),throwable);
                return Result.error("500","服务繁忙");
            }

            @Override
            public Result<SubmitOrderResponse> submitOrder(@Valid SubmitOrderRequest request) {
                log.error("execute ProductApiClient submitOrder error,request:{}", JSON.toJSONString(request),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
