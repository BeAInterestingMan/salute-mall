package com.salute.mall.user.service.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPermissionDTO implements Serializable {

    private String userName;

    private String userCode;

    private List<String> permissionList;
}
