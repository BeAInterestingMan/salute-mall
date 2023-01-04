package com.salute.mall.order.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CancelTypeEnum {

    DELAY_CANCEL("DELAY_CANCEL","超时取消")
    ;

    /**后端含义*/
    private final String desc;

    private final String value;

}
