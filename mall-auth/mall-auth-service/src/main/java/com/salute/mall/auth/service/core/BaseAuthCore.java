package com.salute.mall.auth.service.core;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.service.adapt.AdminUserAdapt;
import com.salute.mall.auth.service.adapt.dto.UserPermissionDTO;
import com.salute.mall.auth.service.dto.AuthUserEntity;
import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import com.salute.mall.auth.service.enums.AuthErrorCodeEnum;
import com.salute.mall.auth.service.factory.AuthenticateFactory;
import com.salute.mall.auth.service.properties.MallAuthProperties;
import com.salute.mall.auth.service.strategy.AuthenticateStrategy;
import com.salute.mall.auth.service.utils.JWTUtil;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.redis.helper.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
@Component
@Slf4j
public class BaseAuthCore {

    @Autowired
    private AuthenticateFactory authenticateFactory;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private AdminUserAdapt adminUserAdapt;

    @Autowired
    private MallAuthProperties mallAuthProperties;

    /**
     * @Description 获取缓存冲token信息
     * @author liuhu
     * @param accessToken
     * @date 2022/12/21 17:21
     * @return com.salute.mall.auth.service.dto.SimpleUserInfoDTO
     */
    public AuthUserEntity getAuthUserEntity(String accessToken) {
        AuthUserEntity tokenInfo = JWTUtil.getUserInfoFromToken(accessToken);
        if(Objects.isNull(tokenInfo)){
            log.error("jwt中获取用户信息异常,accessToken:{}",accessToken);
            throw new BusinessException(AuthErrorCodeEnum.TOKEN_NOT_EXIST.getCode(), AuthErrorCodeEnum.TOKEN_NOT_EXIST.getMsg());
        }
        // JWT无法很好的实现token过期 所以这里使用redis存储
        String cacheTokenInfo = (String)redisHelper.get(RedisConstants.AuthCacheKey.MALL_AUTH_TOKEN_PREFIX + accessToken);
        if(StringUtils.isBlank(cacheTokenInfo)){
            log.error("缓存获取信息为空,accessToken:{}",accessToken);
            throw new BusinessException(AuthErrorCodeEnum.TOKEN_EXPIRE.getCode(), AuthErrorCodeEnum.TOKEN_EXPIRE.getMsg());
        }
        return JSON.parseObject(cacheTokenInfo,AuthUserEntity.class);
    }


    /**
     * @Description 获取缓存冲的用户
     * @author liuhu
     * @param userCode
     * @param systemUserType
     * @date 2022/12/21 17:21
     * @return com.salute.mall.auth.service.dto.SimpleUserInfoDTO
     */
    public SimpleUserInfoDTO cacheAndGetUserInfo(String userCode, String systemUserType) {
        String key = RedisConstants.AuthCacheKey.MALL_AUTH_USER_INFO+userCode;
        String cacheUserInfo = (String)redisHelper.get(key);
        if(StringUtils.isNotBlank(cacheUserInfo)){
            return JSON.parseObject(cacheUserInfo,SimpleUserInfoDTO.class);
        }
        AuthenticateStrategy instance = authenticateFactory.getInstance(systemUserType);
        SimpleUserInfoDTO userInfoDTO = instance.doAuthenticate(userCode, systemUserType);
        redisHelper.set(key,JSON.toJSONString(userInfoDTO),mallAuthProperties.getUserInfoCacheTime(), TimeUnit.MILLISECONDS);
        return userInfoDTO;
    }

   /**
    * @Description 获取缓存冲的用户权限
    * @author liuhu
    * @param userCode
    * @date 2022/12/21 17:24
    * @return com.salute.mall.auth.service.adapt.dto.UserPermissionDTO
    */
    public UserPermissionDTO cacheAndGetUserInfo(String userCode) {
        String key = RedisConstants.AuthCacheKey.MALL_AUTH_USER_PERMISSION_INFO+userCode;
        String cacheUserPermissionInfo = (String)redisHelper.get(key);
        if(StringUtils.isNotBlank(cacheUserPermissionInfo)){
            return JSON.parseObject(cacheUserPermissionInfo,UserPermissionDTO.class);
        }
        UserPermissionDTO permission = adminUserAdapt.getUserPermission(userCode);
        redisHelper.set(key,JSON.toJSONString(permission),mallAuthProperties.getUserPermissionInfoCacheTime(), TimeUnit.MILLISECONDS);
        return permission;
    }
}
