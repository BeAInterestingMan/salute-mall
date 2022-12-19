package com.salute.mall.auth.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SystemUserTypeEnum {

    ADMIN("PC后台用户","ADMIN",""),
    MEMBER("会员用户","MEMBER","");

    private final String name;

    private final String value;

    private final String beanName;


    public static SystemUserTypeEnum getByValue(String value){
        if(StringUtils.isBlank(value)){
            return null;
        }
        for (SystemUserTypeEnum userTypeEnum : SystemUserTypeEnum.values()) {
             if(Objects.equals(userTypeEnum.value,value)){
                return userTypeEnum;
             }
        }
        return null;
    }

}
