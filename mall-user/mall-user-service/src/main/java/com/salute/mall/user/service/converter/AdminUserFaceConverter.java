package com.salute.mall.user.service.converter;

import com.salute.mall.user.api.response.AdminUserSimpleResponse;
import com.salute.mall.user.service.pojo.dto.AdminUserSimpleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminUserFaceConverter {

    AdminUserSimpleResponse convertToAdminUserSimpleDTO(AdminUserSimpleDTO info);
}
