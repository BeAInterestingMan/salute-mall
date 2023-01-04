package com.salute.mall.product.service.pojo.dto.stock;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class OperateRealStockSkuDTO implements Serializable {

    @ApiModelProperty(value = "操作库存数量",name = "stockNum")
    @NotNull
    @Min(-10000)
    @Max(1000)
    private Integer stockNum;

    @ApiModelProperty(value = "商品编号",name = "skuCode")
    @NotBlank
    private String productCode;

    @ApiModelProperty(value = "商品编号",name = "skuCode")
    @NotBlank
    private String skuCode;

    @ApiModelProperty(value = "商品名称",name = "skuName")
    @NotBlank
    private String skuName;
}
