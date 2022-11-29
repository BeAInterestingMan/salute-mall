package com.salute.mall.product.service.pojo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@ApiModel("商品基本信息")
@Data
public class ProductBaseInfoResponse implements Serializable {

    @ApiModelProperty(value = "租户号",name = "tenantCode")
    @TableField("tenant_code")
    private String tenantCode;

    @ApiModelProperty(value = "商品名称",name = "spuName")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty(value = "商品编码",name = "spuCode")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty(value = "销售价",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价",name = "marketPrice")
    @TableField("market_price")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价",name = "costPrice")
    @TableField("cost_price")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    @TableField("main_image")
    private String mainImage;

    @ApiModelProperty(value = "关键词",name = "keyword")
    @TableField("keyword")
    private String keyword;

    @ApiModelProperty(value = "标题",name = "title")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "卖点",name = "sellPoint")
    @TableField("sell_point")
    private String sellPoint;

    @ApiModelProperty(value = "商品条形码",name = "barcode")
    @TableField("barcode")
    private String barcode;
}
