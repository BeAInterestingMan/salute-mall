package com.salute.mall.order.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PayStatusEnum {

    WAIT_PAY("WAIT_PAY","待支付"),
    PAY_SUCCESS("PAY_SUCCESS","支付成功"),
    ;

    ;

    /**后端含义*/
    private final String desc;

    private final String value;

}
