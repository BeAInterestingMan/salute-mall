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
@TableName("product_spu_detail")
@ApiModel(value = "商品spu详情表")
public class ProductSpuDetail implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "spu编号",name = "spuCode")
    @TableField("spu_code")
    private String spuCode;

    @ApiModelProperty(value = "生成日期",name = "productTime")
    @TableField("product_time")
    private Date productTime;

    @ApiModelProperty(value = "商品保质期单位",name = "shelfLifeUnit")
    @TableField("shelf_life_unit")
    private String shelfLifeUnit;

    @ApiModelProperty(value = "商品保质期",name = "shelfLife")
    @TableField("shelf_life")
    private Integer shelfLife;

    @ApiModelProperty(value = "产品详情网页内容",name = "detailHtml")
    @TableField("detail_html")
    private String detailHtml;

    @ApiModelProperty(value = "移动端网页详情",name = "detailMobileHtml")
    @TableField("detail_mobile_html")
    private String detailMobileHtml;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    @TableField("main_image")
    private String mainImage;

    @ApiModelProperty(value = "图片详情",name = "images")
    @TableField("images")
    private String images;

    @ApiModelProperty(value = "产地",name = "originPlace")
    @TableField("origin_place")
    private String originPlace;

    @ApiModelProperty(value = "重量（g）",name = "weight")
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty(value = "删除标志;YES-已删除 NO-正常",name = "deleteFlag")
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
