package com.salute.mall.user.service.converter;

import com.salute.mall.user.service.pojo.dto.AdminUserSimpleDTO;
import com.salute.mall.user.service.pojo.entity.AdminUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminUserServiceConverter {
    AdminUserSimpleDTO convertToAdminUserSimpleDTO(AdminUser adminUser);
}
