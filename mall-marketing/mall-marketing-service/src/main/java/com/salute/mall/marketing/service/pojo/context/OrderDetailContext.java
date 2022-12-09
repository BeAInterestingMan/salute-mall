package com.salute.mall.marketing.service.pojo.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDetailContext implements Serializable {

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "sku名称",name = "skuName")
    private String skuName;

    @ApiModelProperty(value = "商品金额",name = "salePrice")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品购买数量",name = "bugQty")
    private Integer bugQty;

    @ApiModelProperty(value = "商品购买数量",name = "bugQty")
    private String categoryCodeThird;
}
