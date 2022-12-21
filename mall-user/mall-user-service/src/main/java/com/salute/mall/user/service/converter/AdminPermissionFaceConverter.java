package com.salute.mall.user.service.converter;

import com.salute.mall.user.api.response.UserPermissionResponse;
import com.salute.mall.user.service.pojo.dto.UserPermissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminPermissionFaceConverter {
    UserPermissionResponse convertToUserPermissionResponse(UserPermissionDTO userPermission);
}
