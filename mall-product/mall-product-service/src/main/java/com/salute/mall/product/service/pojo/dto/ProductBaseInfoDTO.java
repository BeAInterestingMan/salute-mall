package com.salute.mall.product.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 *  @Description 商品基本信息
 *  @author liuhu
 *  @Date 2022/11/29 20:49
 */
@Data
public class ProductBaseInfoDTO implements Serializable {

    @ApiModelProperty(value = "租户号",name = "tenantCode")
    private String tenantCode;

    @ApiModelProperty(value = "商品名称",name = "spuName")
    private String productName;

    @ApiModelProperty(value = "商品编码",name = "spuCode")
    private String productCode;

    @ApiModelProperty(value = "销售价",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价",name = "marketPrice")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价",name = "costPrice")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "关键词",name = "keyword")
    private String keyword;

    @ApiModelProperty(value = "标题",name = "title")
    private String title;

    @ApiModelProperty(value = "卖点",name = "sellPoint")
    private String sellPoint;

    @ApiModelProperty(value = "商品条形码",name = "barcode")
    private String barcode;
}
