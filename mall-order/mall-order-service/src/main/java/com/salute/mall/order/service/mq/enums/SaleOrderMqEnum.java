package com.salute.mall.order.service.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SaleOrderMqEnum {

    DELAY_ORDER("DELAY_ORDER",null,"订单创建延迟消息"),
    CREATE_ERROR("CREATE_ERROR",null,"订单创建异常，触发回滚");;

    private String topic;

    private String tag;

    private String desc;


}
