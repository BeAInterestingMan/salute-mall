package com.salute.mall.user.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("user/adminUser")
public class AdminUserController {


    @GetMapping
    public void getSimpleUserInfoByUserCode(@NotBlank String userCode){

    }
}
