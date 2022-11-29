package com.salute.mall.product.service.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@ApiModel(value = "小程序商品详情对象")
public class ProductH5DetailResponse implements Serializable {

    @ApiModelProperty(value = "商品基本信息",name = "productBaseInfo")
    private ProductBaseInfoResponse productBaseInfo;

    @ApiModelProperty(value = "商品图文详情",name = "productDetailInfo")
    private ProductDetailInfoResponse productDetailInfo;

    @ApiModelProperty(value = "商品sku信息",name = "productPloySkuInfoList")
    private List<ProductPloySkuInfoResponse> productPloySkuInfoList;

    @ApiModelProperty(value = "商品规格信息",name = "productSpecificationList")
    private List<ProductSpecificationResponse> productSpecificationList;
}