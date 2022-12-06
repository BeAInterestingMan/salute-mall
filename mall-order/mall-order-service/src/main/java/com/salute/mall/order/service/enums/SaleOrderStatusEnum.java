package com.salute.mall.order.service.enums;

public enum SaleOrderStatusEnum {

    WAIT_AUDIT("已创建","待审核","WAIT_AUDIT");

    ;

    /**后端含义*/
    private final String name;

    /**前端展示*/
    private final String frontName;

    private final String value;

    SaleOrderStatusEnum(String name, String frontName, String value) {
        this.name = name;
        this.frontName = frontName;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getFrontName() {
        return frontName;
    }

    public String getValue() {
        return value;
    }
}
