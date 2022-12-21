package com.salute.mall.user.service.pojo.DO;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserPermissionDO implements Serializable {

    private String userName;

    private String userCode;

    private String permission;
}
