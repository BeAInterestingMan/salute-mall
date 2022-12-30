package com.salute.mall.marketing.service.pojo.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderContext implements Serializable {

    @ApiModelProperty(value = "操作人",name = "operator")
    @NotBlank
    private String userName;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    @NotBlank
    private String userCode;

    @ApiModelProperty(value = "订单原始金额",name = "orderOriginTotalAmount")
    private BigDecimal orderOriginAmount;

    @ApiModelProperty(value = "商品详情",name = "orderDetailContextList")
    private List<OrderDetailContext> orderDetailContextList;
}
