package com.salute.mall.auth.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("认证入参")
public class MallAuthenticationRequest implements Serializable {

    @ApiModelProperty(value = "token",name = "accessToken")
    @NotBlank
    private String accessToken;
}
