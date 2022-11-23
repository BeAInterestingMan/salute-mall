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
@TableName("product_spu")
@ApiModel(value = "商品spu表")
public class ProductSpu implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "租户号",name = "tenantCode")
    @TableField("tenant_code")
    private String tenantCode;

    @ApiModelProperty(value = "spu名称",name = "spuName")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty(value = "spu编码",name = "spuCode")
    @TableField("spu_code")
    private String spuCode;

    @ApiModelProperty(value = "供应商编码",name = "supplierCode")
    @TableField("supplier_code")
    private String supplierCode;

    @ApiModelProperty(value = "供应商名称",name = "supplierName")
    @TableField("supplier_name")
    private String supplierName;

    @ApiModelProperty(value = "一级分类编号",name = "categoryCodeFirst")
    @TableField("category_code_first")
    private String categoryCodeFirst;

    @ApiModelProperty(value = "二级分类编号",name = "categoryCodeSencond")
    @TableField("category_code_sencond")
    private String categoryCodeSencond;

    @ApiModelProperty(value = "三级分类编号",name = "categoryCodeThird")
    @TableField("category_code_third")
    private String categoryCodeThird;

    @ApiModelProperty(value = "商品条形码",name = "barcode")
    @TableField("barcode")
    private String barcode;

    @ApiModelProperty(value = "品牌编号",name = "brandCode")
    @TableField("brand_code")
    private String brandCode;

    @ApiModelProperty(value = "销量",name = "saleNum")
    @TableField("sale_num")
    private Integer saleNum;

    @ApiModelProperty(value = "商品状态;ON上架 DOWN下架",name = "status")
    @TableField("status")
    private String status;

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
