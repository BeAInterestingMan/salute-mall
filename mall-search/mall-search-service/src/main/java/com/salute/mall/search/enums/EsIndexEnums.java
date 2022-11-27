package com.salute.mall.search.enums;

public enum EsIndexEnums {

    PRODUCT("salute_mall_product","商品索引")
    ;
    private final String name;

    private final String desc;

    EsIndexEnums(String name, String desc) {
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
