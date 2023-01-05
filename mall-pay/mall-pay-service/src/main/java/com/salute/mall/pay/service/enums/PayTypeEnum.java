package com.salute.mall.pay.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PayTypeEnum {

    ALIPAY_PC("ALIPAY","支付宝网页支付","alipayStrategyService")
    ;

    private final String value;

    private final String desc;

    private final String beanName;


    public static PayTypeEnum getBeanByValue(String value){
        if(StringUtils.isBlank(value)){
            return null;
        }
        for (PayTypeEnum typeEnum : PayTypeEnum.values()) {
            if(Objects.equals(typeEnum.value,value)){
                return typeEnum;
            }
        }
        return null;
    }
}
