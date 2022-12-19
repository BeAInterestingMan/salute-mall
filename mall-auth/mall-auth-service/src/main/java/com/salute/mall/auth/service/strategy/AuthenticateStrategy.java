package com.salute.mall.auth.service.strategy;

import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;

public interface AuthenticateStrategy {
   /**
    * @Description 策略认证
    * @author liuhu
    * @param userCode
    * @param systemUserType
    * @date 2022/12/19 18:57
    * @return com.salute.mall.auth.service.dto.SimpleUserInfoDTO
    */
   SimpleUserInfoDTO doAuthenticate(String userCode, String systemUserType);
}
