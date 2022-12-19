package com.salute.mall.auth.service.adapt.converter;

import com.salute.mall.auth.service.adapt.dto.MemberSimpleInfoDTO;
import com.salute.mall.member.api.response.MemberSimpleInfoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberAdaptConverter {

    MemberSimpleInfoDTO convertToMemberSimpleInfoDTO(MemberSimpleInfoResponse data);
}
