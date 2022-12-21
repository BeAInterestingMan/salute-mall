package com.salute.mall.auth.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum AuthErrorCodeEnum {

    TOKEN_NOT_EXIST("A1000","token解析异常"),
    USER_NOT_EXIST("A1000","用户不存在"),
    TOKEN_EXPIRE("A1000","登录已过期"),
    NO_PERMISSION("A1000","未设置权限"),
    UNAUTHORIZED("A1001","无权访问");

    private final String code;

    private final String msg;
}
