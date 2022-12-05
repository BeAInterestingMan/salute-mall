package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDetailInfoResponse implements Serializable {

     @ApiModelProperty(value = "商品基本信息",name = "productBaseInfo")
     private ProductBaseInfoResponse productBaseInfo;

     @ApiModelProperty(value = "商品图文详情",name = "productDetailInfo")
     private ProductDetailResponse productDetailInfo;

     @ApiModelProperty(value = "商品sku信息",name = "productPloySkuInfoList")
     private List<ProductPloySkuInfoResponse> productPloySkuInfoList;

     @ApiModelProperty(value = "商品规格信息",name = "productSpecificationList")
     private List<ProductSpecificationResponse> productSpecificationList;

     @ApiModelProperty(value = "商品标签信息",name = "productTagBaseList")
     private List<ProductTagInfoResponse> productTagBaseList;

}
