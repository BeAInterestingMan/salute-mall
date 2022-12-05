package com.salute.mall.search.api.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldNameConstants
public class ProductSkuSaveEsRequest implements Serializable {

    @ApiModelProperty(value = "sku名称",name = "skuName")
    @NotBlank
    private String skuName;

    @ApiModelProperty(value = "sku编码",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "售卖价",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价",name = "marketPrice")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价",name = "costPrice")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "商品状态;ON上架 DOWN下架",name = "status")
    private String status;

    @ApiModelProperty(value = "可用库存",name = "availableStock")
    private Integer availableStock;
}
