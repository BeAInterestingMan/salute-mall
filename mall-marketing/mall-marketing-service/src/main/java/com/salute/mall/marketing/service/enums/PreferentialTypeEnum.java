package com.salute.mall.marketing.service.enums;

public enum PreferentialTypeEnum {

    COUPON("COUPON","优惠券"),
    ACTIVITY("ACTIVITY","活动"),
    ;

    private final String name;

    private final String desc;

    PreferentialTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
