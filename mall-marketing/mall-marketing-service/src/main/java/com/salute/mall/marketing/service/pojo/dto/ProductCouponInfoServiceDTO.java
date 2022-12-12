package com.salute.mall.marketing.service.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("商品维度优惠信息")
public class ProductCouponInfoServiceDTO implements Serializable {

    @ApiModelProperty(value = "商品sku编码",name = "skuCode")
    @NotBlank
    private String productCode;

    @ApiModelProperty(value = "门店编号",name = "shopCode")
    @NotBlank
    private String shopCode;

    @ApiModelProperty(value = "商品sku编码",name = "skuCode")
    @NotBlank
    private String skuCode;

    @ApiModelProperty(value = "一级分类编号",name = "categoryCodeFirst")
    @NotBlank
    private String categoryCodeFirst;

    @ApiModelProperty(value = "二级分类编号",name = "categoryCodeSecond")
    @NotBlank
    private String categoryCodeSecond;

    @ApiModelProperty(value = "三级分类编号",name = "categoryCodeThird")
    @NotBlank
    private String categoryCodeThird;
}
