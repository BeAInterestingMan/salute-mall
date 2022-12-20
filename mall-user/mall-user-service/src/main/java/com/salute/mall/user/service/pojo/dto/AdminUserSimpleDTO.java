package com.salute.mall.user.service.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(value = "管理后台用户表")
public class AdminUserSimpleDTO implements Serializable {

    @ApiModelProperty(value = "用户名称",name = "userName")
    private String userName;

    @ApiModelProperty(value = "用户编码",name = "userCode")
    private String userCode;

    @ApiModelProperty(value = "昵称",name = "nickName")
    private String nickName;

    @ApiModelProperty(value = "头像",name = "avatar")
    private String avatar;

    @ApiModelProperty(value = "性别",name = "sex")
    private String sex;
}
