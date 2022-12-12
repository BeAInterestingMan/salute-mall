package com.salute.mall.auth.service.service.impl;

import com.salute.mall.auth.service.service.AuthService;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.redis.helper.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisHelper redisHelper;

    public static String TOKEN_KEY= "MALL_USER_ACCESS_TOKEN";

    @Override
    public void getUserInfoByAccessToken(String accessToken) {
        SaluteAssertUtil.isTrue(StringUtils.isBlank(accessToken),"accessToken不存在");
        //token是否存在
        String token = redisHelper.hGet(TOKEN_KEY, accessToken);
        if(StringUtils.isBlank(token)){
            SaluteAssertUtil.isTrue(StringUtils.isBlank(accessToken),"accessToken已过期");
        }
        // JWT parse

    }
}
