package com.salute.mall.member.service.service.impl;

import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.member.service.converter.MemberServiceConverter;
import com.salute.mall.member.service.pojo.dto.MemberBaseDTO;
import com.salute.mall.member.service.pojo.entity.Member;
import com.salute.mall.member.service.repository.MemberRepository;
import com.salute.mall.member.service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceConverter memberServiceConverter;

    @Autowired
    private RedisHelper redisHelper;

    @Override
    public String login(String encrypt) {
//        String decrypt = RSAUtil.decrypt(encrypt);
//        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(decrypt),"参数异常");
//        MemberLoginDTO loginDTO = JSON.parseObject(decrypt, MemberLoginDTO.class);
//        SaluteAssertUtil.isTrue(Objects.nonNull(loginDTO),"参数异常");
//        Member member = memberRepository.getByMemberName(loginDTO.getUsername());
//        SaluteAssertUtil.isTrue(Objects.nonNull(member),"当前用户不存在");
//        String password = RSAUtil.decrypt(member.getPassword());
//        SaluteAssertUtil.isTrue(!Objects.equals(password,loginDTO.getPassword()),"当前用户不存在");
//        SaluteAssertUtil.isTrue(!Objects.equals(member.getStatus(), MemberStatusEnum.ENABLE.getName()),"当前用户异常");
//        AuthUserEntity userEntity = AuthUserEntity.builder().userCode(member.getMemberCode()).userName(member.getMemberName())
//                .userType(SystemUserTypeEnum.MEMBER.getValue()).build();
//        return genAccessToken(userEntity);
        return null;
    }

    @Override
    public MemberBaseDTO getMemberInfoByMemberCode(String memberCode) {
        Member member = memberRepository.getByMemberCode(memberCode);
        return  memberServiceConverter.convertToMemberBaseDTO(member);
    }
//
//    private String genAccessToken(AuthUserEntity userEntity){
//        String token = JWTUtil.generateToken(userEntity);
//        String accessToken = Base64Utils.encodeToString(SystemUserTypeEnum.MEMBER.getValue().getBytes(StandardCharsets.UTF_8))+token;
//        Boolean flag = redisHelper.hSet(SecurityConstants.ACCESS_TOKEN_PREFIX, accessToken, JSON.toJSONString(accessToken));
//        SaluteAssertUtil.isTrue(Objects.equals(flag,Boolean.TRUE),"获取token失败");
//        return accessToken;
//    }
}
