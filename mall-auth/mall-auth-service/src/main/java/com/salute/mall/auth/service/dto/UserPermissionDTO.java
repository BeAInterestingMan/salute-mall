package com.salute.mall.auth.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserPermissionDTO implements Serializable {

    private String roleName;

    private List<String> permissionList;
}
