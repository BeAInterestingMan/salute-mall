package com.salute.mall.member.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.common.security.constants.SecurityConstants;
import com.salute.mall.common.security.utils.JWTUtil;
import com.salute.mall.common.core.utils.RSAUtil;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.security.dto.AuthUserEntity;
import com.salute.mall.common.security.enums.SystemUserTypeEnum;
import com.salute.mall.member.service.enums.MemberStatusEnum;
import com.salute.mall.member.service.pojo.dto.MemberLoginDTO;
import com.salute.mall.member.service.pojo.entity.Member;
import com.salute.mall.member.service.repository.MemberRepository;
import com.salute.mall.member.service.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RedisHelper redisHelper;

    @Override
    public String login(String encrypt) {
        String decrypt = RSAUtil.decrypt(encrypt);
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(decrypt),"参数异常");
        MemberLoginDTO loginDTO = JSON.parseObject(decrypt, MemberLoginDTO.class);
        SaluteAssertUtil.isTrue(Objects.nonNull(loginDTO),"参数异常");
        Member member = memberRepository.getByMemberName(loginDTO.getUsername());
        SaluteAssertUtil.isTrue(Objects.nonNull(member),"当前用户不存在");
        String password = RSAUtil.decrypt(member.getPassword());
        SaluteAssertUtil.isTrue(!Objects.equals(password,loginDTO.getPassword()),"当前用户不存在");
        SaluteAssertUtil.isTrue(!Objects.equals(member.getStatus(), MemberStatusEnum.ENABLE.getName()),"当前用户异常");
        AuthUserEntity userEntity = AuthUserEntity.builder().userCode(member.getMemberCode()).userName(member.getMemberName())
                .userType(SystemUserTypeEnum.MEMBER.getValue()).build();
        return genAccessToken(userEntity);
    }

   private String genAccessToken(AuthUserEntity userEntity){
        String token = JWTUtil.generateToken(userEntity);
        String accessToken = Base64Utils.encodeToString(SystemUserTypeEnum.MEMBER.getValue().getBytes(StandardCharsets.UTF_8))+token;
        Boolean flag = redisHelper.hSet(SecurityConstants.ACCESS_TOKEN_PREFIX, accessToken, JSON.toJSONString(accessToken));
        SaluteAssertUtil.isTrue(Objects.equals(flag,Boolean.TRUE),"获取token失败");
        return accessToken;
    }
}
