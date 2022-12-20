package com.salute.mall.user.service.service;

import com.salute.mall.user.service.pojo.dto.AdminUserSimpleDTO;

public interface AdminUserService {
    AdminUserSimpleDTO getSimpleUserInfoByUserCode(String userCode);
}
