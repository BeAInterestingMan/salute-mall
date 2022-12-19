package com.salute.mall.member.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.member.api.response.MemberSimpleInfoResponse;
import com.salute.mall.member.service.converter.MemberFaceConverter;
import com.salute.mall.member.service.pojo.dto.MemberBaseDTO;
import com.salute.mall.member.service.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("member/")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberFaceConverter memberFaceConverter;

    @PostMapping("login")
    public String login(String encrypt){
      return  memberService.login(encrypt);
    }

    @PostMapping("getMemberSimpleInfoByMemberCode")
    public Result<MemberSimpleInfoResponse> getMemberSimpleInfoByMemberCode(@NotBlank String memberCode){
        log.info("execute getMemberSimpleInfoByMemberCode info,req:{}",memberCode);
        MemberBaseDTO memberBaseDTO = memberService.getMemberInfoByMemberCode(memberCode);
        MemberSimpleInfoResponse response =  memberFaceConverter.convertToMemberSimpleInfoResponse(memberBaseDTO);
        log.info("execute getMemberSimpleInfoByMemberCode info,req:{},resp:{}",memberCode, JSON.toJSONString(response));
        return Result.success(response);
    }

}
