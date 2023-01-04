package com.salute.mall.order.service.adapt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("使用优惠券入参")
public class UseCouponAdaptDTO implements Serializable {

    /**业务单哈*/
    @ApiModelProperty(value = "业务单号",name = "bizCode")
    @NotBlank
    private String bizCode;

    @ApiModelProperty(value = "优惠券编号",name = "couponCode")
    @NotBlank
    private String couponCode;

    @ApiModelProperty(value = "操作人名称",name = "skuName")
    @NotBlank
    private String operator;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    @NotBlank
    private String operateCode;
}
