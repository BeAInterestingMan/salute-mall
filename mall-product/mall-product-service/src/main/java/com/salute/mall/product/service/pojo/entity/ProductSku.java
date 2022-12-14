package com.salute.mall.product.service.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("product_sku")
@ApiModel(value = "商品sku表")
public class ProductSku implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;


    @ApiModelProperty(value = "sku名称",name = "skuName")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "sku编码",name = "skuCode")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    @TableField("main_image")
    private String mainImage;

    @ApiModelProperty(value = "商品编码",name = "productCode")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "是否是默认sku  NO-否 YES-是",name = "productCode")
    @TableField("default_sku_flag")
    private String defaultSkuFlag;

    @ApiModelProperty(value = "sku对应规格json",name = "specificationJson")
    @TableField("specification_json")
    private String specificationJson;

    @ApiModelProperty(value = "售卖价",name = "salePrice")
    @TableField("sale_price")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价",name = "marketPrice")
    @TableField("market_price")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价",name = "costPrice")
    @TableField("cost_price")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "商品状态;ON上架 DOWN下架",name = "status")
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
