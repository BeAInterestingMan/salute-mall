package com.salute.mall.auth.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("认证入参")
public class MallAuthenticateRequest implements Serializable {

    @ApiModelProperty(value = "用户编号",name = "userCode")
    private String userCode;

    @ApiModelProperty(value = "用户类型",name = "systemUserType")
    private String systemUserType;
}
