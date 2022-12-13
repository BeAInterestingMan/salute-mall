package com.salute.mall.common.security.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthUserEntity implements Serializable {

    /**用户名称*/
    private String userName;

    /**用户编号*/
    private String userCode;

    /**头像*/
    private String avatar;

    /**用户类型 MEMBER会员 PC 后台用户*/
    private String userType;
}
