package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  @Description 商品sku聚合信息
 *  @author liuhu
 *  @Date 2022/11/29 20:49
 */
@Data
public class ProductBaseSkuResponse implements Serializable {

    @ApiModelProperty(value = "sku名称",name = "skuName")
    private String skuName;

    @ApiModelProperty(value = "sku编码",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "商品编码",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "是否是默认sku  NO-否 YES-是",name = "productCode")
    private String defaultSkuFlag;

    @ApiModelProperty(value = "售卖价",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价",name = "marketPrice")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价",name = "costPrice")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "可用库存",name = "availableStock")
    private Integer availableStock;
}
