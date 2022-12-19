package com.salute.mall.member.service.converter;

import com.salute.mall.member.api.response.MemberSimpleInfoResponse;
import com.salute.mall.member.service.pojo.dto.MemberBaseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberFaceConverter {

    MemberSimpleInfoResponse convertToMemberSimpleInfoResponse(MemberBaseDTO memberBaseDTO);
}
