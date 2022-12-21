package com.salute.mall.auth.service.service.impl;

import com.salute.mall.auth.service.adapt.dto.UserPermissionDTO;
import com.salute.mall.auth.service.core.BaseAuthCore;
import com.salute.mall.auth.service.dto.AuthUserEntity;
import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import com.salute.mall.auth.service.enums.AuthErrorCodeEnum;
import com.salute.mall.auth.service.service.AuthenticateService;
import com.salute.mall.auth.service.utils.JWTUtil;
import com.salute.mall.common.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private BaseAuthCore baseAuthCore;

    @Override
    public SimpleUserInfoDTO authenticate(String accessToken) {
        // 1.从缓存中获取token信息
        AuthUserEntity authUserEntity = baseAuthCore.getAuthUserEntity(accessToken);
        // 2,获取用户信息
        SimpleUserInfoDTO userInfoDTO = baseAuthCore.cacheAndGetUserInfo(authUserEntity.getUserCode(), authUserEntity.getSystemUserType());
        if (Objects.isNull(userInfoDTO)) {
            throw new BusinessException(AuthErrorCodeEnum.USER_NOT_EXIST.getCode(), AuthErrorCodeEnum.USER_NOT_EXIST.getMsg());
        }
         return userInfoDTO;
    }


    @Override
    public void authorization(String userCode, String uri) {
        UserPermissionDTO permission = baseAuthCore.cacheAndGetUserInfo(userCode);
        if(Objects.isNull(permission)){
            throw new BusinessException(AuthErrorCodeEnum.NO_PERMISSION.getCode(), AuthErrorCodeEnum.NO_PERMISSION.getMsg());
        }
        if(!permission.getPermissionList().contains(uri)){
            throw new BusinessException(AuthErrorCodeEnum.UNAUTHORIZED.getCode(), AuthErrorCodeEnum.UNAUTHORIZED.getMsg());
        }
    }
}
