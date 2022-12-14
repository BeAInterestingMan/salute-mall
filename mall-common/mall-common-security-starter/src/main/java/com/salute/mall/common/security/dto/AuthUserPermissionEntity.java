package com.salute.mall.common.security.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthUserPermissionEntity implements Serializable {

    /**用户名称*/
    private String userName;

    /**用户编号*/
    private String userCode;

    /**权限url*/
    private List<String> permissionList;
}
