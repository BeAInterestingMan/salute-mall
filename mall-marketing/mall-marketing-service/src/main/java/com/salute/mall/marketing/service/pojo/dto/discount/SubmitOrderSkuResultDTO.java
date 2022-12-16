package com.salute.mall.marketing.service.pojo.dto.discount;

import com.salute.mall.marketing.service.pojo.dto.PrepareOrderDetailServiceDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("提交订单计算优惠入参")
public class SubmitOrderSkuResultDTO implements Serializable {


    @ApiModelProperty(value = "sku编号",name = "skuCode")
    @NotBlank
    private String skuCode;

    @ApiModelProperty(value = "商品金额",name = "salePrice")
    @NotBlank
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品购买数量",name = "bugQty")
    @NotNull
    @Max(10000)
    @Min(1)
    private Integer bugQty;

    @ApiModelProperty(value = "商品金额",name = "originAmount")
    @NotBlank
    private BigDecimal originAmount;

    @ApiModelProperty(value = "商品总金额",name = "totalAmount")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "商品优惠金额",name = "totalPreferentialAmount")
    private BigDecimal totalPreferentialAmount;

    @ApiModelProperty(value = "商品优惠后金额",name = "totalFinalAmount")
    private BigDecimal totalFinalAmount;
}
