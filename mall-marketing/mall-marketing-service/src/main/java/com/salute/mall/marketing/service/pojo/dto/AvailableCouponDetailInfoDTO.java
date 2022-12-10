package com.salute.mall.marketing.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableCouponDetailInfoDTO implements Serializable {

    @ApiModelProperty(value = "sku编码",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "价格",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "购买数量",name = "buyCount")
    private Integer buyCount;
}
