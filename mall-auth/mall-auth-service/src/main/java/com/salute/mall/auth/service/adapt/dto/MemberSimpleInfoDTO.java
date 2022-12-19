package com.salute.mall.auth.service.adapt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberSimpleInfoDTO implements Serializable {

    @ApiModelProperty(value = "会员编号",name = "memberCode")
    private String memberCode;

    @ApiModelProperty(value = "会员名称",name = "memberName")
    private String memberName;

    @ApiModelProperty(value = "昵称",name = "nickName")
    private String nickName;

    @ApiModelProperty(value = "性别",name = "sex")
    private String sex;
}
