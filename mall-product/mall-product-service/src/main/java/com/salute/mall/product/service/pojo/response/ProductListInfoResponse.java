package com.salute.mall.product.service.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品列表出参")
public class ProductListInfoResponse {

    @ApiModelProperty(value = "spu编号",name = "spuCode")
    private String spuCode;

    @ApiModelProperty(value = "spu名称",name = "spuName")
    private String spuName;

    @ApiModelProperty(value = "商品销量",name = "saleNum")
    private Long saleNum;

    @ApiModelProperty(value = "商品主图",name = "mainImage")
    private String mainImage;

    @ApiModelProperty(value = "评价数",name = "evaluationNum")
    private Long evaluationNum;

    @ApiModelProperty(value = "店铺编号",name = "shopCode")
    private String shopCode;

    @ApiModelProperty(value = "店铺名称",name = "shopName")
    private String shopName;
}
