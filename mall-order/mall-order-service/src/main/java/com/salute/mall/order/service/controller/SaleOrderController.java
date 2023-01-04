package com.salute.mall.order.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.order.service.convert.SaleOrderCustomerFaceConverter;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderDTO;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderResultDTO;
import com.salute.mall.order.service.pojo.request.CreateSaleOrderRequest;
import com.salute.mall.order.service.pojo.response.CreateSaleOrderResponse;
import com.salute.mall.order.service.service.SaleOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order/customer/")
@Api(tags = "C端创建订单")
@Slf4j
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private SaleOrderCustomerFaceConverter saleOrderCustomerFaceConverter;

    @GetMapping("getSaleOrderCode")
    @ApiOperation("获取订单编号")
    public Result<String> getSaleOrderCode(){
        return Result.success(saleOrderService.getSaleOrderCode());
    }

    @GetMapping("createSaleOrder")
    @ApiOperation("创建订单")
    public Result<CreateSaleOrderResponse> createSaleOrder(CreateSaleOrderRequest  request){
        log.info("execute createSaleOrder info req:{}", JSON.toJSONString(request));
        CreateSaleOrderDTO dto = saleOrderCustomerFaceConverter.convertToCreateSaleOrderDTO(request);
        CreateSaleOrderResultDTO saleOrderResultDTO = saleOrderService.createSaleOrder(dto);
        CreateSaleOrderResponse response  = saleOrderCustomerFaceConverter.convertToCreateSaleOrderResponse(saleOrderResultDTO);
        log.info("execute createSaleOrder success req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(response));
        return Result.success(response);
    }
}
