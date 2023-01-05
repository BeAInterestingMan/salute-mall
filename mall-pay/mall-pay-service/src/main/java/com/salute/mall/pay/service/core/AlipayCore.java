package com.salute.mall.pay.service.core;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.pay.service.pojo.dto.AlipayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.AlipayPayBaseDTO;
import com.salute.mall.pay.service.properties.AlipayProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AlipayCore {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayProperty alipayProperty;


    /**
     * @Description 支付宝支付
     * @author liuhu
     * @param alipayPayBaseDTO
     * @date 2023/1/5 16:50
     * @return java.lang.String
     */
    public String openAliPay(AlipayPayBaseDTO alipayPayBaseDTO){
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayProperty.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayProperty.getNotifyUrl());
        AlipayBaseDTO alipayBaseDTO = buildAlipayBaseDTO(alipayPayBaseDTO);
        alipayRequest.setBizContent(JSON.toJSONString(BeanUtil.beanToMap(alipayBaseDTO)));
        //请求
        try {
             AlipayTradePagePayResponse payResponse = alipayClient.pageExecute(alipayRequest);
             if(!payResponse.isSuccess()){
                 log.error("调用支付宝唤起支付异常，req:{},resp:{}",JSON.toJSONString(alipayRequest), JSON.toJSONString(payResponse));
                 throw new BusinessException("500","调用支付宝唤起支付异常");
             }
             return payResponse.getBody();
        } catch (AlipayApiException e) {
            log.info("调用支付宝付款接口异常，req:{}", JSON.toJSONString(alipayRequest),e);
            throw new BusinessException("500","调用支付宝唤起支付异常");
        }
    }

    private AlipayBaseDTO buildAlipayBaseDTO(AlipayPayBaseDTO alipayPayBaseDTO) {
        AlipayBaseDTO alipayBaseDTO = new AlipayBaseDTO();
        alipayBaseDTO.setOut_trade_no(alipayPayBaseDTO.getTradeCode());
        alipayBaseDTO.setProduct_code(alipayPayBaseDTO.getProductCode());
        alipayBaseDTO.setBody(alipayPayBaseDTO.getRemark());
        alipayBaseDTO.setSubject(alipayPayBaseDTO.getSubject());
        alipayBaseDTO.setTotal_amount(String.valueOf(alipayPayBaseDTO.getPayableAmount()));
        return alipayBaseDTO;
    }
}
