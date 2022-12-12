package com.salute.mall.marketing.service.enums;

public enum CouponActivityStatusEnum {

    NOT_START("NOT_START","未开始"),
    RUNNING("RUNNING","进行中"),
    REPEAL("已撤销","REPEAL"),
    ;

    private final String name;

    private final String value;

    CouponActivityStatusEnum(String name, String value) {
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
