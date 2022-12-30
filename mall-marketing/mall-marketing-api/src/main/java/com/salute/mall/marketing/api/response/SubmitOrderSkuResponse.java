package com.salute.mall.marketing.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("提交订单计算优惠商品详情")
public class SubmitOrderSkuResponse implements Serializable {

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "商品名称",name = "productName")
    private String skuName;

    @ApiModelProperty(value = "商品金额",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品购买数量",name = "bugQty")
    private Integer bugQty;

    @ApiModelProperty(value = "商品总金额",name = "productOriginAmount")
    private BigDecimal productOriginAmount;

    @ApiModelProperty(value = "商品优惠金额",name = "productPreferentialAmount")
    private BigDecimal productPreferentialAmount;

    @ApiModelProperty(value = "商品优惠后金额",name = "productFinalAmount")
    private BigDecimal productFinalAmount;

}
