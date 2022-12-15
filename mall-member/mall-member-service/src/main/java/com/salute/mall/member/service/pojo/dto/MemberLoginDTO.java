package com.salute.mall.member.service.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberLoginDTO implements Serializable {

    private String username;

    private String password;
}
