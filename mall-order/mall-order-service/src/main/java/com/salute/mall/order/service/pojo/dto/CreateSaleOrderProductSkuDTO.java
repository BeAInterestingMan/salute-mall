package com.salute.mall.order.service.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("创建订单商品详情入参")
public class CreateSaleOrderProductSkuDTO implements Serializable {

    @ApiModelProperty(value = "sku编号",name = "productCode")
    @NotBlank
    private String productCode;

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    @NotBlank
    private String skuCode;

    @ApiModelProperty(value = "sku名称",name = "skuName")
    @NotBlank
    private String skuName;

    @ApiModelProperty(value = "购买数量",name = "buyQty")
    @NotNull
    @Max(10000)
    @Min(1)
    private Integer buyQty;

    @ApiModelProperty(value = "赠品   正常购买商品",name = "orderSkuType")
    @TableField("order_sku_type")
    @NotBlank
    private String orderSkuType;

    @ApiModelProperty(value = "销售金额（未参与营销）",name = "salePrice")
    @TableField("sale_price")
    @NotNull
    @Max(10000)
    @Min(1)
    private BigDecimal salePrice;

    @ApiModelProperty(value = "应付金额",name = "payableAmount")
    @TableField("payable_amount")
    @NotNull
    @Max(10000)
    @Min(1)
    private BigDecimal payableAmount;

}
