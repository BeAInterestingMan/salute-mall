package com.salute.mall.common.core.enums;

public enum CommonStatusEnum {


    YES("是","YES"),

    NO("NO","YES");

    private final String name;

    private final String value;

    CommonStatusEnum(String name, String value) {
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
