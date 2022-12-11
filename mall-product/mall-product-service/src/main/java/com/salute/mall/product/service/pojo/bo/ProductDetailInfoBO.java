package com.salute.mall.product.service.pojo.bo;

import com.salute.mall.product.service.pojo.dto.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDetailInfoBO implements Serializable {

     @ApiModelProperty(value = "商品信息",name = "productDetail")
     private ProductBaseDTO productDetail;

     @ApiModelProperty(value = "商品sku信息",name = "productSkuList")
     private List<ProductSkuDTO> productSkuList;

     @ApiModelProperty(value = "商品规格信息",name = "productSpecificationList")
     private List<ProductSpecificationDTO> productSpecificationList;

     @ApiModelProperty(value = "商品标签信息",name = "productTagBaseList")
     private List<ProductTagBaseDTO> productTagBaseList;

}
