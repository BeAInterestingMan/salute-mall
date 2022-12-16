package com.salute.mall.marketing.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("提交订单计算优惠入参")
public class SubmitOrderRequest implements Serializable {

    @ApiModelProperty(value = "订单编号",name = "couponCode")
    @NotBlank
    private String orderCode;

    @ApiModelProperty(value = "优惠券单号（推荐时不传  用户可手动选择其他优惠券）",name = "couponCode")
    private String couponCode;

    @ApiModelProperty(value = "操作人",name = "operator")
    @NotBlank
    private String userName;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    @NotBlank
    private String userCode;

    @ApiModelProperty(value = "商品详情",name = "detailList")
    @NotEmpty
    private List<PrepareOrderDetailRequest> detailList;
}
