package com.salute.mall.product.service.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("商品规格信息")
public class ProductSpecificationResponse implements Serializable {

    @ApiModelProperty(value = "商品编号",name = "spuCode")
    private String productCode;

    @ApiModelProperty(value = "规格项编号",name = "specificationCode")
    private String specificationCode;

    @ApiModelProperty(value = "规格项名称",name = "specificationName")
    private String specificationName;

    @ApiModelProperty(value = "规格项值",name = "specificationValue")
    private List<String> specificationValueList;
}
