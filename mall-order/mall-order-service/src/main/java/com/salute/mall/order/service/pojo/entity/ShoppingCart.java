package com.salute.mall.order.service.pojo.entity;

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
@TableName("shopping_cart")
@ApiModel(value = "购物车")
public class ShoppingCart implements Serializable {

    @TableId
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    @ApiModelProperty(value = "商品编码",name = "skuCode")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty(value = "商品sku名称",name = "skuName")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "商品编码",name = "productCode")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "购买数量",name = "buyQty")
    @TableField("buy_qty")
    private Integer buyQty;

    @ApiModelProperty(value = "店铺编号",name = "shopCode")
    @TableField("shop_code")
    private String shopCode;

    @ApiModelProperty(value = "店铺名称",name = "shopName")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "加入购物车时价格",name = "addCartPrice")
    @TableField("add_cart_price")
    private BigDecimal addCartPrice;

    @ApiModelProperty(value = "sku主图",name = "mainImage")
    @TableField("main_image")
    private String mainImage;

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
