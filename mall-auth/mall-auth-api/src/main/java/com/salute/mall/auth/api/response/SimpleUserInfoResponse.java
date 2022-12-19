package com.salute.mall.auth.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SimpleUserInfoResponse implements Serializable {

    @ApiModelProperty(value = "会员编号",name = "memberCode")
    private String userCode;

    @ApiModelProperty(value = "会员名称",name = "memberName")
    private String userName;

    @ApiModelProperty(value = "昵称",name = "nickName")
    private String nickName;

    @ApiModelProperty(value = "性别",name = "sex")
    private String sex;

    @ApiModelProperty(value = "系统用户类型",name = "systemUserType")
    private String systemUserType;

    @ApiModelProperty(value = "用户头像",name = "avatar")
    private String avatar;
}
