package com.salute.mall.pay.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PayStatusEnum {

    WAIT_PAY("WAIT_PAY","待支付"),

    PAY_SUCCESS("PAY_SUCCESS","支付成功"),
    ;

    private final String value;

    private final String desc;



    public static PayStatusEnum getBeanByValue(String value){
        if(StringUtils.isBlank(value)){
            return null;
        }
        for (PayStatusEnum typeEnum : PayStatusEnum.values()) {
            if(Objects.equals(typeEnum.value,value)){
                return typeEnum;
            }
        }
        return null;
    }
}
