package com.salute.mall.member.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.member.service.mapper.MemberMapper;
import com.salute.mall.member.service.pojo.entity.Member;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberRepository {

    @Autowired
    private MemberMapper memberMapper;

    public Member getByMemberName(String username) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(username),"用户名不能为空");
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getMemberName,username);
        return memberMapper.selectOne(queryWrapper);
    }
}
