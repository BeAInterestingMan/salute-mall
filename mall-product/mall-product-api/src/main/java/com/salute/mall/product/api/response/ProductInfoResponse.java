package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductInfoResponse implements Serializable {

     @ApiModelProperty(value = "商品基本信息",name = "productBaseInfo")
     private ProductBaseResponse productBaseInfo;

     @ApiModelProperty(value = "商品图文详情",name = "productDetailInfo")
     private ProductDetailBaseResponse productDetailInfo;

     @ApiModelProperty(value = "商品sku信息",name = "productSkuList")
     private List<ProductSkuResponse> productSkuList;

     @ApiModelProperty(value = "商品标签信息",name = "productTagBaseList")
     private List<ProductBaseTagResponse> productTagBaseList;

}
