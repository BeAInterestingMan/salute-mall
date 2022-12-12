package com.salute.mall.auth.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthUserDTO implements Serializable {

    private String username;

    private String userCode;
}
