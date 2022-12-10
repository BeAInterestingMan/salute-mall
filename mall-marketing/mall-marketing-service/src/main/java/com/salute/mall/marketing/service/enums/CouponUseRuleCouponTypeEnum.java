package com.salute.mall.marketing.service.enums;

import java.util.Objects;

public enum CouponUseRuleCouponTypeEnum {

    ALL("无门槛","ALL"),

    AMOUNT_SATISFY("满足指定金额","AMOUNT_SATISFY")
    ;

    private final String name;

    private final String value;

    CouponUseRuleCouponTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static CouponUseRuleCouponTypeEnum getByValue(String value){
        for (CouponUseRuleCouponTypeEnum typeEnum : CouponUseRuleCouponTypeEnum.values()) {
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
