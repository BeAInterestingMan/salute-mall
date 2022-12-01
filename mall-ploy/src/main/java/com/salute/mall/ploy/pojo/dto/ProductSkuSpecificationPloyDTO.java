package com.salute.mall.ploy.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  @Description 商品sku规格信息
 *  @author liuhu
 *  @Date 2022/11/29 20:30
 */
@Data
public class ProductSkuSpecificationPloyDTO {

    @ApiModelProperty(value = "商品编号",name = "spuCode")
    private String productCode;

    @ApiModelProperty(value = "规格项编号",name = "specificationCode")
    private String specificationCode;

    @ApiModelProperty(value = "规格项名称",name = "specificationName")
    private String specificationName;

    @ApiModelProperty(value = "规格项值",name = "specificationValue")
    private String specificationValue;
}
