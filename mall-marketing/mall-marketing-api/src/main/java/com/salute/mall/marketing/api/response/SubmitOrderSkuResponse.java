package com.salute.mall.marketing.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("提交订单计算优惠入参")
public class SubmitOrderSkuResponse implements Serializable {

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



}
