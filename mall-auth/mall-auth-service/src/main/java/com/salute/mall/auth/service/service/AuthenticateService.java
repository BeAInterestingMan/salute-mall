package com.salute.mall.auth.service.service;

import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;

public interface AuthenticateService {
    /**
     * @Description 用户认证策略实现
     * @author liuhu
     * @param userCode
     * @param systemUserType
     * @date 2022/12/19 18:33
     * @return com.salute.mall.auth.service.dto.SimpleUserInfoDTO
     */
    SimpleUserInfoDTO authenticate(String userCode, String systemUserType);
}
