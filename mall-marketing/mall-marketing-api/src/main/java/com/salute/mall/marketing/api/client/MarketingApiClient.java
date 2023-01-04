package com.salute.mall.marketing.api.client;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.marketing.api.fallback.MarketingApiClientFallback;
import com.salute.mall.marketing.api.request.PrepareOrderRequest;
import com.salute.mall.marketing.api.request.ReturnCouponRequest;
import com.salute.mall.marketing.api.request.SubmitOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.PrepareOrderResponse;
import com.salute.mall.marketing.api.response.SubmitOrderResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@FeignClient(value = "mall-marketing", contextId = "saluteMarketingApiClient", fallbackFactory = MarketingApiClientFallback.class)
public interface MarketingApiClient {

    @PostMapping("marketing/coupon/api/useCoupon")
    @ApiOperation("使用优惠券")
    Result<Void> useCoupon(@Valid UseCouponRequest request);

    @PostMapping("marketing/coupon/api/returnCoupon")
    @ApiOperation("归还优惠券")
    Result<Void> returnCoupon(@Valid ReturnCouponRequest request);

    @PostMapping("marketing/coupon/api/prepareOrder")
    @ApiOperation("提交订单时推荐优惠券、计算优惠金额")
    Result<PrepareOrderResponse> prepareOrder(@Valid PrepareOrderRequest request);

    @PostMapping("marketing/coupon/api/submitOrder")
    @ApiOperation("提交订单使用优惠券、计算优惠分摊")
    Result<SubmitOrderResponse> submitOrder(@Valid SubmitOrderRequest request);
}
