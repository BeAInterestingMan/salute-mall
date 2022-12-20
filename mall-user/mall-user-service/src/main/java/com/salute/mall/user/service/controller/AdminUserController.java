package com.salute.mall.user.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.user.api.response.AdminUserSimpleResponse;
import com.salute.mall.user.service.converter.AdminUserFaceConverter;
import com.salute.mall.user.service.pojo.dto.AdminUserSimpleDTO;
import com.salute.mall.user.service.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("user/adminUser/")
@Slf4j
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminUserFaceConverter adminUserFaceConverter;

    @GetMapping("getSimpleUserInfo")
    public Result<AdminUserSimpleResponse> getSimpleUserInfo(@NotBlank String userCode){
        log.info("execute getSimpleUserInfo info,req:{}", JSON.toJSONString(userCode));
        AdminUserSimpleDTO info = adminUserService.getSimpleUserInfoByUserCode(userCode);
        AdminUserSimpleResponse response = adminUserFaceConverter.convertToAdminUserSimpleDTO(info);
        log.info("execute getSimpleUserInfo info,req:{},resp:{}", JSON.toJSONString(userCode), JSON.toJSONString(response));
        return Result.success(response);
    }
}
