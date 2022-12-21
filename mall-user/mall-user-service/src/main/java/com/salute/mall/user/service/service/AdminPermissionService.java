package com.salute.mall.user.service.service;

import com.salute.mall.user.service.pojo.DO.UserPermissionDO;
import com.salute.mall.user.service.pojo.dto.UserPermissionDTO;

public interface AdminPermissionService {
    /**
     * @Description 获取用户权限
     * @author liuhu
     * @param userCode
     * @date 2022/12/21 16:51
     * @return com.salute.mall.user.service.pojo.DO.UserPermissionDO
     */
    UserPermissionDTO getUserPermission(String userCode);
}
