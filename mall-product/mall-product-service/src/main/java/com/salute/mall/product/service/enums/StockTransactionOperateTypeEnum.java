package com.salute.mall.product.service.enums;

public enum StockTransactionOperateTypeEnum {

    FREEZING_STOCK("冻结库存","FREEZING_STOCK"),
    REDUCE_REAL_STOCK("冻结库存","REDUCE_REAL_STOCK"),
    INCREASE_REAL_STOCK("增加真实库存","INCREASE_REAL_STOCK")

    ;

    private final String name;

    private final String value;


    StockTransactionOperateTypeEnum(String name, String value) {
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
