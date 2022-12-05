package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductResponse implements Serializable {

    @ApiModelProperty(value = "商品名称",name = "spuName")
    private String productName;

    @ApiModelProperty(value = "商品编码",name = "spuCode")
    private String productCode;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "关键词",name = "keyword")
    private String keyword;

    @ApiModelProperty(value = "标题",name = "title")
    private String title;

    @ApiModelProperty(value = "卖点",name = "sellPoint")
    private String sellPoint;

    @ApiModelProperty(value = "供应商编码",name = "supplierCode")
    private String supplierCode;

    @ApiModelProperty(value = "供应商名称",name = "supplierName")
    private String supplierName;

    @ApiModelProperty(value = "一级分类编号",name = "categoryCodeFirst")
    private String categoryCodeFirst;

    @ApiModelProperty(value = "二级分类编号",name = "categoryCodeSencond")
    private String categoryCodeSecond;

    @ApiModelProperty(value = "三级分类编号",name = "categoryCodeThird")
    private String categoryCodeThird;

    @ApiModelProperty(value = "商品条形码",name = "barcode")
    private String barcode;

    @ApiModelProperty(value = "品牌编号",name = "brandCode")
    private String brandCode;

    @ApiModelProperty(value = "销量",name = "saleNum")
    private Integer saleNum;

    @ApiModelProperty(value = "商品状态;ON上架 DOWN下架",name = "status")
    private String status;

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
