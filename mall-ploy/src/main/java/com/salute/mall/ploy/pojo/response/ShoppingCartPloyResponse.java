package com.salute.mall.ploy.pojo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ShoppingCartPloyResponse implements Serializable {

    @ApiModelProperty(value = "店铺编号",name = "shopCode")
    private String shopCode;

    @ApiModelProperty(value = "店铺名称",name = "shopName")
    private String shopName;

    @ApiModelProperty(value = "店铺下商品详情",name = "shoppingCartDetailList")
    private List<ShoppingCartDetailResponse> shoppingCartDetailList;
}
