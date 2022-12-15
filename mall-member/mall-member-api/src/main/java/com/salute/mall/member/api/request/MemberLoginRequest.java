package com.salute.mall.member.api.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class MemberLoginRequest implements Serializable {

    private String userName;

    private String password;
}
