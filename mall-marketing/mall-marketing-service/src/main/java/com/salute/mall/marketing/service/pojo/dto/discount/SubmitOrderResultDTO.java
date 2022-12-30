package com.salute.mall.marketing.service.pojo.dto.discount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("提交订单计算优惠入参")
public class SubmitOrderResultDTO implements Serializable {

    @ApiModelProperty(value = "使用的优惠券单号",name = "couponCode")
    private String couponCode;

    @ApiModelProperty(value = "订单金额",name = "couponCode")
    private BigDecimal orderOriginAmount;

    @ApiModelProperty(value = "优惠券折扣金额",name = "couponDiscountAmount")
    private BigDecimal couponPreferentialAmount;

    @ApiModelProperty(value = "订单最终金额",name = "orderFinalAmount")
    private BigDecimal orderFinalAmount;

    @ApiModelProperty(value = "优惠券原始金额",name = "couponAmount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "商品分摊详情",name = "skuPreferentialList")
    private List<SubmitOrderSkuResultDTO> skuPreferentialList;


}
