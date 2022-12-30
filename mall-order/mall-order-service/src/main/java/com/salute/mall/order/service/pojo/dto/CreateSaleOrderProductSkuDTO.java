package com.salute.mall.order.service.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("创建订单商品详情入参")
public class CreateSaleOrderProductSkuDTO implements Serializable {

    @ApiModelProperty(value = "sku编号",name = "productCode")
    private String productCode;

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    private String skuCode;

    @ApiModelProperty(value = "sku名称",name = "skuName")
    private String skuName;

    @ApiModelProperty(value = "购买数量",name = "buyQty")
    private Integer buyQty;

    @ApiModelProperty(value = "赠品   正常购买商品",name = "orderSkuType")
    private String orderSkuType;

    @ApiModelProperty(value = "销售金额（未参与营销）",name = "salePrice")
    private BigDecimal salePrice;

}
