package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDetailCustomInfoResponse implements Serializable {

     @ApiModelProperty(value = "商品基本信息",name = "productDetail")
     private ProductCustomInfoResponse productDetail;

     @ApiModelProperty(value = "商品sku信息",name = "productSkuList")
     private List<ProductSkuResponse> productSkuList;

     @ApiModelProperty(value = "商品规格信息",name = "productSpecificationList")
     private List<ProductSpecificationResponse> productSpecificationList;

     @ApiModelProperty(value = "商品标签信息",name = "productTagBaseList")
     private List<ProductTagResponse> productTagBaseList;

}
