package com.salute.mall.marketing.service.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public enum CouponUserRecordUseTypeEnum {

    PRODUCT("指定商品可用","PRODUCT"),
    CATEGORY("指定分类可用","CATEGORY"),
    ALL_PRODUCT("全部商品可用","ALL_PRODUCT"),
    ;

    private final String name;

    private final String value;

    CouponUserRecordUseTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static CouponUserRecordUseTypeEnum getByValue(String value){
        for (CouponUserRecordUseTypeEnum typeEnum : CouponUserRecordUseTypeEnum.values()) {
            if(Objects.equals(typeEnum.getValue(),value)){
                return typeEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
