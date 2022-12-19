package com.salute.mall.member.service.converter;

import com.salute.mall.member.service.pojo.dto.MemberBaseDTO;
import com.salute.mall.member.service.pojo.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberServiceConverter {

    MemberBaseDTO convertToMemberBaseDTO(Member member);
}
