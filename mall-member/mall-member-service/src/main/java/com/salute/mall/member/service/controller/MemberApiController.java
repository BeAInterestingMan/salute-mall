package com.salute.mall.member.service.controller;

import com.salute.mall.member.service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MemberApiController {

    @Autowired
    private MemberService memberService;

}
