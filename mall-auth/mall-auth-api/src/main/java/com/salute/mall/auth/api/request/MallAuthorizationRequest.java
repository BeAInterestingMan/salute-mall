package com.salute.mall.auth.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("认证入参")
public class MallAuthorizationRequest implements Serializable {

    @ApiModelProperty(value = "用户编号",name = "userCode")
    @NotBlank
    private String userCode;

    @ApiModelProperty(value = "请求uri",name = "uri")
    @NotBlank
    private String uri;
}
