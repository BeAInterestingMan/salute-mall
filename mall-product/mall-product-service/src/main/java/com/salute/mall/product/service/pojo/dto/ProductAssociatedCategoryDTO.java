package com.salute.mall.product.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductAssociatedCategoryDTO implements Serializable {

    @ApiModelProperty(value = "分类编号",name = "categoryCode")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称",name = "categoryName")
    private String categoryName;

    @ApiModelProperty(value = "分类图片",name = "categoryImage")
    private String categoryImage;
}
