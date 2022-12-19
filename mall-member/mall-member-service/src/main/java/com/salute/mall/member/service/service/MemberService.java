package com.salute.mall.member.service.service;

import com.salute.mall.member.service.pojo.dto.MemberBaseDTO;

public interface MemberService {

    String login(String encrypt);

    /**
     * @Description 通过会员编号获取会员信息
     * @author liuhu
     * @param memberCode
     * @date 2022/12/19 16:57
     * @return com.salute.mall.member.service.pojo.dto.MemberBaseDTO
     */
    MemberBaseDTO getMemberInfoByMemberCode(String memberCode);
}
