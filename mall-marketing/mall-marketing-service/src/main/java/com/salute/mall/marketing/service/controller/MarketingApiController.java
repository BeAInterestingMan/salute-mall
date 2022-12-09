package com.salute.mall.marketing.service.controller;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.marketing.api.request.PrepareOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.PrepareOrderResponse;
import com.salute.mall.marketing.service.converter.MarketingApiFaceConverter;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderServiceDTO;
import com.salute.mall.marketing.service.pojo.dto.UseCouponServiceDTO;
import com.salute.mall.marketing.service.service.MarketingApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("marketing/coupon/api")
@Api(tags = "营销-通用api")
public class MarketingApiController {

    @Autowired
    private MarketingApiService marketingApiService;

    @Autowired
    private MarketingApiFaceConverter marketingApiFaceConverter;

    @PostMapping("useCoupon")
    @ApiOperation("使用优惠券")
    public Result<Void> useCoupon(@Valid UseCouponRequest request){
        UseCouponServiceDTO dto = marketingApiFaceConverter.convertToUseCouponServiceDTO(request);
        marketingApiService.useCoupon(dto);
        return Result.success();
    }


    @PostMapping("prepareOrder")
    @ApiOperation("提交订单时推荐优惠券、计算优惠金额")
    public Result<PrepareOrderResponse> prepareOrder(@Valid PrepareOrderRequest request){
        PrepareOrderServiceDTO dto = marketingApiFaceConverter.convertToPrepareOrderServiceDTO(request);
        marketingApiService.prepareOrder(dto);
        return Result.success();
    }
}
