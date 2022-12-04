package com.salute.mall.product.service.pojo.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.salute.mall.product.service.pojo.dto.ProductTagBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductListInfoBO implements Serializable {

    @ApiModelProperty(value = "商品编号",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "商品名称",name = "productName")
    private String productName;

    @ApiModelProperty(value = "商品销量",name = "saleNum")
    private Long saleNum;

    @ApiModelProperty(value = "sku名称",name = "skuName")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "sku编码",name = "skuCode")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty(value = "销售价",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "市场价",name = "marketPrice")
    @TableField("market_price")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价",name = "costPrice")
    @TableField("cost_price")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "评价数",name = "evaluationNum")
    private Long evaluationNum;

    @ApiModelProperty(value = "店铺编号",name = "shopCode")
    private String shopCode;

    @ApiModelProperty(value = "店铺名称",name = "shopName")
    private String shopName;

    @ApiModelProperty(value = "商品标签",name = "productTagList")
    private List<ProductTagBaseDTO> productTagList;
}
