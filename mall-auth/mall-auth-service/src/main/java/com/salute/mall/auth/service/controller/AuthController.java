package com.salute.mall.auth.service.controller;

import com.salute.mall.auth.service.service.AuthService;
import com.salute.mall.common.core.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@RequestMapping("auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/user/getUserInfo")
    public Result<Void> getUserInfoByAccessToken(@NotBlank String accessToken){
        authService.getUserInfoByAccessToken(accessToken);
        return Result.success();
    }
}
