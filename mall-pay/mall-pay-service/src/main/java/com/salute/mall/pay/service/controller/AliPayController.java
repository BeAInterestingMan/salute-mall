package com.salute.mall.pay.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.pay.api.request.OpenAlipayRequest;
import com.salute.mall.pay.service.converter.AlipayConverter;
import com.salute.mall.pay.service.pojo.dto.AlipayPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO;
import com.salute.mall.pay.service.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("pay/alipay/")
@RestController
@Slf4j
public class AliPayController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AlipayConverter alipayConverter;


    @GetMapping("pay")
    public Result<String> pay(@RequestBody OpenAlipayRequest request){
        log.info("execute pay info,req:{}", JSON.toJSONString(request));
        AlipayPayBaseDTO alipayPayBaseDTO =  alipayConverter.convertToAlipayPayBaseDTO(request);
        OpenPayCenterDTO payCenterDTO = alipayService.openAliPay(alipayPayBaseDTO);
        log.info("execute pay info,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(payCenterDTO));
        return Result.success(payCenterDTO.getPayUrl());
    }
}
