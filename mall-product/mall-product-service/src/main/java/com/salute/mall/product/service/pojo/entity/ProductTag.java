package com.salute.mall.product.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Data
@TableName("product_tag")
@ApiModel(value = "商品标签表")
public class ProductTag implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "商品编码",name = "productCode")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "标签描述",name = "tagDesc")
    @TableField("tag_desc")
    private String tagDesc;

    @ApiModelProperty(value = "标签code",name = "tagCode")
    @TableField("tag_code")
    private String tagCode;

    @ApiModelProperty(value = "标签名称",name = "tagName")
    @TableField("tag_name")
    private String tagName;

    @ApiModelProperty(value = "状态 ON上架 DOWN下架",name = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "删除标志;YES-已删除 NO-正常",name = "deleteFlag")
    @TableField("delete_flag")
    private String deleteFlag;

    @ApiModelProperty(value = "排序",name = "sort")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "乐观锁",name = "version")
    @TableField("version")
    private Integer version;

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
