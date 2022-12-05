package com.salute.mall.search.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductTagEsEntity implements Serializable {

    @ApiModelProperty(value = "标签code",name = "tagCode")
    private String tagCode;

    @ApiModelProperty(value = "标签名称",name = "tagName")
    private String tagName;
}
