package com.salute.mall.product.service.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@ApiModel(value = "小程序商品分类")
public class ProductCategoryResp implements Serializable {

    @ApiModelProperty(value = "租户号",name = "tenantCode")
    private String tenantCode;

    @ApiModelProperty(value = "分类级别",name = "categoryLevel")
    private String categoryLevel;

    @ApiModelProperty(value = "父级分类code",name = "parentCategoryCode")
    private String parentCategoryCode;

    @ApiModelProperty(value = "分类编号",name = "categoryCode")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称",name = "categoryName")
    private String categoryName;

    @ApiModelProperty(value = "分类描述",name = "categoryDesc")
    private String categoryDesc;

    @ApiModelProperty(value = "分类图标",name = "icon")
    private String icon;

    @ApiModelProperty(value = "排序",name = "sort")
    private Integer sort;

    @ApiModelProperty(value = "子分类",name = "childrenList")
    private List<ProductCategoryResp> childrenList;
}