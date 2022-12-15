package com.salute.mall.member.service.enums;

public enum MemberStatusEnum {

    ENABLE("生效","ENABLE"),
    DISABLE("禁用","DISABLE"),
    LOCKED("锁定","LOCKED");

    private final String name;

    private final String value;


    MemberStatusEnum(String name, String value) {
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
