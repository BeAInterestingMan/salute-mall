package com.salute.mall.marketing.service.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("提交订单计算优惠入参")
@AllArgsConstructor
@NoArgsConstructor
public class PrepareOrderDTO implements Serializable {

    @ApiModelProperty(value = "使用的优惠券单号",name = "couponCode")
    private String couponCode;

    @ApiModelProperty(value = "订单金额",name = "couponCode")
    private String orderAmount;

    @ApiModelProperty(value = "优惠券折扣金额",name = "couponDiscountAmount")
    private String couponDiscountAmount;

    @ApiModelProperty(value = "订单最终金额",name = "orderFinalAmount")
    private String orderFinalAmount;

    @ApiModelProperty(value = "优惠券原始金额",name = "couponAmount")
    private String couponAmount;

    @ApiModelProperty(value = "可用优惠券列表",name = "availableCouponCodeList")
    private List<String> availableCouponCodeList;

    @ApiModelProperty(value = "不可用优惠券列表",name = "notAvailableCouponCodeList")
    private List<String> notAvailableCouponCodeList;
}
