package com.salute.mall.product.service.pojo.response;

import com.salute.mall.product.api.response.ProductTagResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("小程序搜索商品列表出参")
public class ProductListInfoResponse {

    @ApiModelProperty(value = "商品编号",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "商品名称",name = "productName")
    private String productName;

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "sku名称",name = "skuName")
    private String skuName;

    @ApiModelProperty(value = "商品销量",name = "saleNum")
    private Long saleNum;

    @ApiModelProperty(value = "销售价",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "评价数",name = "evaluationNum")
    private Long evaluationNum;

    @ApiModelProperty(value = "店铺编号",name = "shopCode")
    private String shopCode;

    @ApiModelProperty(value = "店铺名称",name = "shopName")
    private String shopName;

    @ApiModelProperty(value = "商品标签",name = "productTagList")
    private List<ProductTagResponse> productTagList;
}
