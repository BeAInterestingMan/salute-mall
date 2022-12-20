package com.salute.mall.auth.service.adapt.converter;

import com.salute.mall.auth.service.adapt.dto.AdminUserSimpleInfoDTO;
import com.salute.mall.user.api.response.AdminUserSimpleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminUserAdaptConverter {
    AdminUserSimpleInfoDTO convertToAdminUserSimpleInfoDTO(AdminUserSimpleResponse data);
}
