package com.salute.mall.marketing.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ReceiveCouponDTO implements Serializable {

    @ApiModelProperty(value = "优惠券活动编号",name = "couponActivityCode")
    @NotBlank
    private String couponActivityCode;

    @ApiModelProperty(value = "用户编号",name = "userCode")
    @NotBlank
    private String userCode;

    @ApiModelProperty(value = "用户姓名",name = "userName")
    @NotBlank
    private String userName;
}
