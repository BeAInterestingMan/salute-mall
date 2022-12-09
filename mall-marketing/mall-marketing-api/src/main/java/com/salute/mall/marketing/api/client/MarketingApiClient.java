package com.salute.mall.marketing.api.client;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.marketing.api.fallback.MarketingApiClientFallback;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@FeignClient(value = "mall-marketing", contextId = "saluteMarketingApiClient", fallbackFactory = MarketingApiClientFallback.class)
public interface MarketingApiClient {

    @PostMapping("marketing/coupon/api/useCoupon")
    Result<Void> useCoupon(@Valid UseCouponRequest request);
}
