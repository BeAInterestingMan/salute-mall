package com.salute.mall.product.service.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "商品spu表")
@TableName("product_spu")
@Data
public class ProductSpu implements Serializable {

     @ApiModelProperty(name = "id")
     @TableId
     private Long id;

     @ApiModelProperty(name = "租户号")
     private String tenantCode;

     @ApiModelProperty(name = "spu名称")
     private String spuName;

     @ApiModelProperty(name = "spu编码")
     private String spuCode;

     @ApiModelProperty(name = "供应商编码")
     private String supplierCode;

     @ApiModelProperty(name = "供应商名称")
     private String supplierName;

     @ApiModelProperty(name = "一级分类编号")
     private String categoryCodeFirst;

     @ApiModelProperty(name = "二级分类编号")
     private String categoryCodeSecond;

     @ApiModelProperty(name = "三级分类编号")
     private String categoryCodeThird;

     @ApiModelProperty(name = "商品条形码")
     private String barcode;

     @ApiModelProperty(name = "品牌编号")
     private String brandCode;

     @ApiModelProperty(name = "名称编号")
     private String shopCode;

     @ApiModelProperty(name = "商品主图")
     private String mainImage;

     @ApiModelProperty(name = "商品图片json")
     private String images;

     @ApiModelProperty(name = "商品状态", notes = "ON上架 DOWN下架")
     private String status;

     @ApiModelProperty(name = "乐观锁")
     private String version;

     @ApiModelProperty(name = "创建人")
     private String creator;

     @ApiModelProperty(name = "创建人编号")
     private String creatorCode;

     @ApiModelProperty(name = "创建时间")
     private Date createdTime;

     @ApiModelProperty(name = "更新人")
     private String modifier;

     @ApiModelProperty(name = "更新人编号")
     private String modifierCode;

     @ApiModelProperty(name = "更新时间")
     private Date modifiedTime;
 }