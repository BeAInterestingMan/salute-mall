package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductTagResponse implements Serializable {

    @ApiModelProperty(value = "商品编码",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "标签code",name = "tagCode")
    private String tagCode;

    @ApiModelProperty(value = "标签名称",name = "tagName")
    private String tagName;

}
