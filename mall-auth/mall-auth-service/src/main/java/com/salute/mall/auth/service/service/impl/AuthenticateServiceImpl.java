package com.salute.mall.auth.service.service.impl;

import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import com.salute.mall.auth.service.factory.AuthenticateFactory;
import com.salute.mall.auth.service.service.AuthenticateService;
import com.salute.mall.auth.service.strategy.AuthenticateStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private AuthenticateFactory authenticateFactory;

    @Override
    public SimpleUserInfoDTO authenticate(String userCode, String systemUserType) {
        AuthenticateStrategy instance = authenticateFactory.getInstance(systemUserType);
        return instance.doAuthenticate(userCode, systemUserType);
    }
}
