package com.salute.mall.member.service.controller;

import com.salute.mall.member.service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("")
    public String login(String encrypt){
      return  memberService.login(encrypt);
    }

}
