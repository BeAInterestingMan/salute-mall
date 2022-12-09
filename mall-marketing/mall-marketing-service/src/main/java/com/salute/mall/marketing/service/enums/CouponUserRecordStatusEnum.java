package com.salute.mall.marketing.service.enums;

public enum CouponUserRecordStatusEnum {

    RECEIVED("已领取","RECEIVED"),
    USED("已使用","USED"),
    REPEAL("已撤销","REPEAL"),
    ;

    private final String name;

    private final String value;

    CouponUserRecordStatusEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
