package com.salute.mall.user.service.service.impl;

import com.salute.mall.user.service.converter.AdminUserServiceConverter;
import com.salute.mall.user.service.pojo.dto.AdminUserSimpleDTO;
import com.salute.mall.user.service.pojo.entity.AdminUser;
import com.salute.mall.user.service.repository.AdminUserRepository;
import com.salute.mall.user.service.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminUserServiceConverter adminUserServiceConverter;

    @Override
    public AdminUserSimpleDTO getSimpleUserInfoByUserCode(String userCode) {
        AdminUser adminUser = adminUserRepository.getUserInfoByUserCode(userCode);
        return adminUserServiceConverter.convertToAdminUserSimpleDTO(adminUser);
    }
}
