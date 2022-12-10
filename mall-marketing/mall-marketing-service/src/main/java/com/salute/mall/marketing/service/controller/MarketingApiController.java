package com.salute.mall.marketing.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.marketing.api.request.PrepareOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.PrepareOrderResponse;
import com.salute.mall.marketing.service.converter.MarketingApiFaceConverter;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderDTO;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderServiceDTO;
import com.salute.mall.marketing.service.pojo.dto.UseCouponServiceDTO;
import com.salute.mall.marketing.service.service.MarketingApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("marketing/coupon/api")
@Api(tags = "营销-通用api")
@Slf4j
public class MarketingApiController {

    @Autowired
    private MarketingApiService marketingApiService;

    @Autowired
    private MarketingApiFaceConverter marketingApiFaceConverter;

    @PostMapping("useCoupon")
    @ApiOperation("使用优惠券")
    public Result<Void> useCoupon(@Valid UseCouponRequest request){
        log.info("execute useCoupon info,req:{}", JSON.toJSONString(request));
        UseCouponServiceDTO dto = marketingApiFaceConverter.convertToUseCouponServiceDTO(request);
        marketingApiService.useCoupon(dto);
        return Result.success();
    }


    @PostMapping("prepareOrder")
    @ApiOperation("提交订单时推荐优惠券、计算优惠金额")
    public Result<PrepareOrderResponse> prepareOrder(@Valid PrepareOrderRequest request){
        log.info("execute prepareOrder info,req:{}", JSON.toJSONString(request));
        PrepareOrderServiceDTO dto = marketingApiFaceConverter.convertToPrepareOrderServiceDTO(request);
        PrepareOrderDTO orderDTO = marketingApiService.prepareOrder(dto);
        PrepareOrderResponse response = marketingApiFaceConverter.convertToPrepareOrderResponse(orderDTO);
        log.info("execute prepareOrder info,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(response));
        return Result.success(response);
    }
}
