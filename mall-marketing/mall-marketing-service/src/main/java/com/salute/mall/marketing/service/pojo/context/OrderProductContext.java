package com.salute.mall.marketing.service.pojo.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderProductContext implements Serializable {

    @ApiModelProperty(value = "订单号",name = "saleOrderCode")
    private String saleOrderCode;

    @ApiModelProperty(value = "订单总金额",name = "orderAmount")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "操作人",name = "operator")
    @NotBlank
    private String userName;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    @NotBlank
    private String userCode;
}
