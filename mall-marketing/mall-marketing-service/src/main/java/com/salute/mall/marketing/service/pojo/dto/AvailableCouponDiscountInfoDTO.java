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
public class AvailableCouponDiscountInfoDTO implements Serializable {

    @ApiModelProperty(value = "优惠券编号",name = "couponCode")
    private String couponCode;

    @ApiModelProperty(value = "优惠券活动名称",name = "couponName")
    private String couponName;

    @ApiModelProperty(value = "订单金额",name = "couponCode")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "优惠券折扣金额",name = "couponPreferentialAmount")
    private BigDecimal couponPreferentialAmount;

    @ApiModelProperty(value = "订单最终金额",name = "orderFinalAmount")
    private BigDecimal orderFinalAmount;

    @ApiModelProperty(value = "优惠券原始金额",name = "couponAmount")
    private BigDecimal couponAmount;
}
