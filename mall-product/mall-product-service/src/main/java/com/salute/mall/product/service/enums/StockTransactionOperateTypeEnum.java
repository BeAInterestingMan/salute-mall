package com.salute.mall.product.service.enums;

public enum StockTransactionOperateTypeEnum {

    REDUCE_FREEZING_STOCK("减少冻结库存（取消订单 减去冻结库存）","REDUCE_FREEZING_STOCK"),
    INCREASE_FREEZING_STOCK("增加冻结库存（下单加冻结库存）","INCREASE_FREEZING_STOCK"),
    REDUCE_REAL_STOCK("扣除真实库存（发货-扣真实库存-减少冻结库存）","REDUCE_REAL_STOCK"),
    INCREASE_REAL_STOCK("增加真实库存（退货收货-增加真实库存）","INCREASE_REAL_STOCK"),

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
