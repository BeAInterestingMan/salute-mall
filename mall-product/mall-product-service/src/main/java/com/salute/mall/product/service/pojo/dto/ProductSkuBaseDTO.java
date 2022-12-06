package com.salute.mall.product.service.pojo.dto;

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
public class ProductSkuBaseDTO implements Serializable {

    @ApiModelProperty(value = "sku名称",name = "skuName")
    private String skuName;

    @ApiModelProperty(value = "sku编码",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "商品编码",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "sku对应规格json",name = "specificationJson")
    private String specificationJson;

    @ApiModelProperty(value = "售卖价",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价",name = "marketPrice")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价",name = "costPrice")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "商品状态;ON上架 DOWN下架",name = "status")
    private String status;

    @ApiModelProperty(value = "排序",name = "sort")
    private Integer sort;

    @ApiModelProperty(value = "创建人",name = "creator")
    private String creator;

    @ApiModelProperty(value = "创建人编号",name = "creatorCode")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间",name = "createdTime")
    private Date createdTime;

    @ApiModelProperty(value = "更新人",name = "modifier")
    private String modifier;

    @ApiModelProperty(value = "更新人编号",name = "modifierCode")
    private String modifierCode;

    @ApiModelProperty(value = "更新时间",name = "modifiedTime")
    private Date modifiedTime;
}
