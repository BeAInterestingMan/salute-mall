package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *  @Description 商品sku聚合信息
 *  @author liuhu
 *  @Date 2022/11/29 20:49
 */
@Data
public class ProductSkuResponse implements Serializable {

    @ApiModelProperty(value = "商品skuId")
    private String skuId;

    @ApiModelProperty(value = "库存")
    private Integer quantity;

    @ApiModelProperty(value = "sku规格信息",name = "specValues")
    private List<ProductSpecificationResponse> specValues;
}
