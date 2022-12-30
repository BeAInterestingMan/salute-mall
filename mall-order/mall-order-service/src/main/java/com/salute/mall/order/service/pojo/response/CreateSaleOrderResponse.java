package com.salute.mall.order.service.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("创建订单入参")
public class CreateSaleOrderResponse implements Serializable {

    @ApiModelProperty(value = "订单编号",name = "saleOrderCode")
    private String saleOrderCode;

    @ApiModelProperty(value = "应付金额",name = "payableAmount")
    private Date createTime;
}
