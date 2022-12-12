package com.salute.mall.common.security.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthUserEntity implements Serializable {

    private String userName;

    private String userCode;
}
