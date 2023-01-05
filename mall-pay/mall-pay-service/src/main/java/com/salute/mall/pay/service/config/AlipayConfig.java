package com.salute.mall.pay.service.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.salute.mall.pay.service.properties.AlipayProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
@Configuration
public class AlipayConfig implements Serializable {

    @Autowired
    private AlipayProperty alipayProperty;

    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(alipayProperty.getGatewayUrl(),
                alipayProperty.getAppId(),
                alipayProperty.getAppPrivateKey(),
                alipayProperty.getFORMAT(),
                alipayProperty.getCHARSET(),
                alipayProperty.getAlipayPublicKey(),
                alipayProperty.getSIGN_TYPE());
    }
}
