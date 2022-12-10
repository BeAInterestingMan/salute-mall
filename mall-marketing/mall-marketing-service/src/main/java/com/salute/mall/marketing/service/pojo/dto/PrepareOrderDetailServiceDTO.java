package com.salute.mall.marketing.service.pojo.dto;

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
@ApiModel("提交订单计算优惠-详情入参")
public class PrepareOrderDetailServiceDTO implements Serializable {

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    @NotBlank
    private String skuCode;

    @ApiModelProperty(value = "sku名称",name = "skuName")
    @NotBlank
    private String skuName;

    @ApiModelProperty(value = "商品金额",name = "salePrice")
    @NotBlank
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品三级分类",name = "categoryCodeThird")
    private String categoryCodeThird;

    @ApiModelProperty(value = "商品购买数量",name = "bugQty")
    @NotNull
    @Max(10000)
    @Min(1)
    private Integer bugQty;
}
