package com.salute.mall.user.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.user.api.response.UserPermissionResponse;
import com.salute.mall.user.service.converter.AdminPermissionFaceConverter;
import com.salute.mall.user.service.pojo.dto.UserPermissionDTO;
import com.salute.mall.user.service.service.AdminPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("user/menu/")
@Slf4j
public class AdminPermissionController {

    @Autowired
    private AdminPermissionService adminPermissionService;

    @Autowired
    private AdminPermissionFaceConverter permissionFaceConverter;

    @GetMapping("getUserPermission")
    public Result<UserPermissionResponse> getUserPermission(@NotBlank String userCode){
        log.info("execute getUserPermission info,req:{}", JSON.toJSONString(userCode));
        UserPermissionDTO userPermission = adminPermissionService.getUserPermission(userCode);
        UserPermissionResponse response = permissionFaceConverter.convertToUserPermissionResponse(userPermission);
        log.info("execute getUserPermission info,req:{},resp:{}", JSON.toJSONString(userCode), JSON.toJSONString(response));
        return Result.success(response);
    }
}
