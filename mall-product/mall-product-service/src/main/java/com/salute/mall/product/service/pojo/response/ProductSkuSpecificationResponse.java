package com.salute.mall.product.service.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *  @Description 商品sku规格信息
 *  @author liuhu
 *  @Date 2022/11/29 20:30
 */
@Data
@ApiModel("商品sku规格信息")
public class ProductSkuSpecificationResponse {

    @ApiModelProperty(value = "商品编号",name = "spuCode")
    private String productCode;

    @ApiModelProperty(value = "规格项编号",name = "specificationCode")
    private String specificationCode;

    @ApiModelProperty(value = "规格项名称",name = "specificationName")
    private String specificationName;

    @ApiModelProperty(value = "规格项值",name = "specificationValue")
    private List<String> specificationValue;
}
