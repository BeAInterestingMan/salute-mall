package com.salute.mall.auth.service.converter;

import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticateFaceConverter {

    SimpleUserInfoResponse convertToSimpleUserInfoResponse(SimpleUserInfoDTO simpleUserInfoDTO);
}
