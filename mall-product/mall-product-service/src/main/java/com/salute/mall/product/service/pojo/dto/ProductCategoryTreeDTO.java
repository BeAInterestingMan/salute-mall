package com.salute.mall.product.service.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@ApiModel(value = "商品分类树DTO")
public class ProductCategoryTreeDTO implements Serializable {

    @ApiModelProperty(value = "id",name = "id")
    private Long id;

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

    @ApiModelProperty(value = "状态",name = "status")
    private String status;

    @ApiModelProperty(value = "删除标志;YES-已删除  NO-正常",name = "deleteFlag")
    private String deleteFlag;

    @ApiModelProperty(value = "排序",name = "sort")
    private Integer sort;

    @ApiModelProperty(value = "创建人",name = "creator")
    private String creator;

    @ApiModelProperty(value = "创建人编号",name = "creatorCode")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间",name = "createdTime")
    private Date createdTime;

    @ApiModelProperty(value = "商品分类子分类",name = "createdTime")
    private List<ProductCategoryTreeDTO> childrenList;
}