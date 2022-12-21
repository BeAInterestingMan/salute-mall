package com.salute.mall.auth.service.service;

import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;

public interface AuthenticateService {
    /**
     * @Description 用户认证策略实现
     * @author liuhu
     * @param accessToken
     * @date 2022/12/19 18:33
     * @return com.salute.mall.auth.service.dto.SimpleUserInfoDTO
     */
    SimpleUserInfoDTO authenticate(String accessToken);

    /**
     * @Description 授权
     * @author liuhu
     * @param userCode
     * @param systemUserType
     * @date 2022/12/21 17:39
     * @return void
     */
    void authorization(String userCode, String systemUserType);
}
