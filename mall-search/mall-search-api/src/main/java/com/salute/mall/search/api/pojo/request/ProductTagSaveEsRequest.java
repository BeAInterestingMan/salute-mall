package com.salute.mall.search.api.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ProductTagSaveEsRequest implements Serializable {

    @ApiModelProperty(value = "商品编码",name = "productCode")
    @NotBlank
    private String productCode;

    @ApiModelProperty(value = "标签code",name = "tagCode")
    private String tagCode;

    @ApiModelProperty(value = "标签名称",name = "tagName")
    private String tagName;
}
