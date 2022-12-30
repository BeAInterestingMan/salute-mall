package com.salute.mall.marketing.service.pojo.dto.discount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("提交订单计算优惠入参")
public class SubmitOrderSkuResultDTO implements Serializable {

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    @NotBlank
    private String skuCode;

    @ApiModelProperty(value = "商品名称",name = "productName")
    @NotBlank
    private String skuName;

    @ApiModelProperty(value = "商品金额",name = "salePrice")
    @NotBlank
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品购买数量",name = "bugQty")
    @NotNull
    @Max(10000)
    @Min(1)
    private Integer bugQty;

    @ApiModelProperty(value = "商品总金额",name = "productOriginAmount")
    private BigDecimal productOriginAmount;

    @ApiModelProperty(value = "商品优惠金额",name = "productPreferentialAmount")
    private BigDecimal productPreferentialAmount;

    @ApiModelProperty(value = "商品优惠后金额",name = "productFinalAmount")
    private BigDecimal productFinalAmount;
}
