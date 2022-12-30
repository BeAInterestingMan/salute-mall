package com.salute.mall.marketing.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("提交订单计算优惠入参")
public class PrepareOrderResponse implements Serializable {

    @ApiModelProperty(value = "使用的优惠券单号",name = "couponCode")
    private String couponCode;

    @ApiModelProperty(value = "订单金额",name = "orderOriginAmount")
    private String orderOriginAmount;

    @ApiModelProperty(value = "优惠券折扣金额",name = "couponPreferentialAmount")
    private String couponPreferentialAmount;

    @ApiModelProperty(value = "订单最终金额",name = "orderFinalAmount")
    private String orderFinalAmount;

    @ApiModelProperty(value = "优惠券原始金额",name = "couponAmount")
    private String couponAmount;

    @ApiModelProperty(value = "可用优惠券列表 用于用户切换",name = "availableCouponCodeList")
    private List<String> availableCouponCodeList;

    @ApiModelProperty(value = "不可用优惠券列表 用于展示",name = "notAvailableCouponCodeList")
    private List<String> notAvailableCouponCodeList;
}
