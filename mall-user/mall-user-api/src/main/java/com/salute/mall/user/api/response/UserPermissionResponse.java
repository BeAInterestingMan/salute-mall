package com.salute.mall.user.api.response;

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
public class UserPermissionResponse implements Serializable {

    private String userName;

    private String userCode;

    private List<String> permissionList;
}
