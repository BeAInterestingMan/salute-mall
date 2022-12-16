package com.salute.mall.marketing.service.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("提交订单计算优惠入参")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrepareOrderDTO implements Serializable {

    @ApiModelProperty(value = "使用的优惠券单号",name = "couponCode")
    private String couponCode;

    @ApiModelProperty(value = "订单金额",name = "couponCode")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "优惠券折扣金额",name = "couponDiscountAmount")
    private BigDecimal couponDiscountAmount;

    @ApiModelProperty(value = "订单最终金额",name = "orderFinalAmount")
    private BigDecimal orderFinalAmount;

    @ApiModelProperty(value = "优惠券原始金额",name = "couponAmount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "可用优惠券列表",name = "availableCouponCodeList")
    private List<String> availableCouponCodeList;

    @ApiModelProperty(value = "不可用优惠券列表",name = "notAvailableCouponCodeList")
    private List<String> notAvailableCouponCodeList;
}
