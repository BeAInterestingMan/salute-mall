package com.salute.mall.order.service.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelOrderPO {

    private String saleOrderCode;

    private String reason;

    private String cancelType;

    private String operator;

    private String operateCode;
}
