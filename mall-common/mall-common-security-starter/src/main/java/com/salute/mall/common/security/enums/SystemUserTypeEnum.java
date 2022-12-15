package com.salute.mall.common.security.enums;

public enum SystemUserTypeEnum {

    PC("PC后台用户","PC"),
    MEMBER("会员用户","MEMBER");

    private final String name;

    private final String value;


    SystemUserTypeEnum(String name, String value) {
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
