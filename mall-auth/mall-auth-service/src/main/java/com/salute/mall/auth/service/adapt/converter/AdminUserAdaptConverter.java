package com.salute.mall.auth.service.adapt.converter;

import com.salute.mall.auth.service.adapt.dto.AdminUserSimpleInfoDTO;
import com.salute.mall.auth.service.adapt.dto.UserPermissionDTO;
import com.salute.mall.user.api.response.AdminUserSimpleResponse;
import com.salute.mall.user.api.response.UserPermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminUserAdaptConverter {
    AdminUserSimpleInfoDTO convertToAdminUserSimpleInfoDTO(AdminUserSimpleResponse data);

    UserPermissionDTO convertToUserPermissionDTO(UserPermissionResponse data);
}
