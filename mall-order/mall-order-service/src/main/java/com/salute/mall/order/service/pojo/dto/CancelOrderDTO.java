package com.salute.mall.order.service.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelOrderDTO {

    private String saleOrderCode;

    private String reason;

    private String cancelType;

    private String operator;

    private String operateCode;
}
