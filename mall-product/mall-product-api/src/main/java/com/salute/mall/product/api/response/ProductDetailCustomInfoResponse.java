package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ProductDetailCustomInfoResponse implements Serializable {

     @ApiModelProperty(value = "商品基本信息",name = "productDetail")
     private ProductCustomInfoResponse data;

     @ApiModelProperty(value = "商品sku信息",name = "specs")
     private List<ProductSkuResponse> specs;

     private Map<String, Object> promotionMap;
}
