package com.salute.mall.auth.service.converter;

import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticateStrategyConverter {
    SimpleUserInfoDTO convertToSimpleUserInfoDTO(String userCode);
}
