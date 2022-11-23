package com.salute.mall.product.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("product_category")
@ApiModel(value = "商品spu数据库")
public class ProductCategory implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "租户号",name = "tenantCode")
    @TableField("tenant_code")
    private String tenantCode;

    @ApiModelProperty(value = "分类级别",name = "categoryLevel")
    @TableField("category_level")
    private String categoryLevel;

    @ApiModelProperty(value = "父级分类code",name = "parentCategoryCode")
    @TableField("parent_category_code")
    private String parentCategoryCode;

    @ApiModelProperty(value = "分类编号",name = "categoryCode")
    @TableField("category_code")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称",name = "categoryName")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty(value = "分类描述",name = "categoryDesc")
    @TableField("category_desc")
    private String categoryDesc;

    @ApiModelProperty(value = "分类图标",name = "icon")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "状态",name = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "删除标志;YES-已删除  NO-正常",name = "deleteFlag")
    @TableField("delete_flag")
    private String deleteFlag;

    @ApiModelProperty(value = "排序",name = "sort")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "乐观锁",name = "version")
    @TableField("version")
    private String version;

    @ApiModelProperty(value = "创建人",name = "creator")
    @TableField("creator")
    private String creator;

    @ApiModelProperty(value = "创建人编号",name = "creatorCode")
    @TableField("creator_code")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间",name = "createdTime")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty(value = "更新人",name = "modifier")
    @TableField("modifier")
    private String modifier;

    @ApiModelProperty(value = "更新人编号",name = "modifierCode")
    @TableField("modifier_code")
    private String modifierCode;

    @ApiModelProperty(value = "更新时间",name = "modifiedTime")
    @TableField("modified_time")
    private Date modifiedTime;

}