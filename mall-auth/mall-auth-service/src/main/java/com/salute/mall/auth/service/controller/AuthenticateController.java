package com.salute.mall.auth.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.api.request.MallAuthenticateRequest;
import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.auth.service.converter.AuthenticateFaceConverter;
import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import com.salute.mall.auth.service.service.AuthenticateService;
import com.salute.mall.common.core.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/authenticate")
@Slf4j
public class AuthenticateController {

     @Autowired
     private AuthenticateService authenticateService;

     @Autowired
     private AuthenticateFaceConverter authenticateFaceConverter;

     @PostMapping
     public Result<SimpleUserInfoResponse> authenticate(@RequestBody MallAuthenticateRequest request){
          log.info("execute authenticate info,req:{}", JSON.toJSONString(request));
          SimpleUserInfoDTO simpleUserInfoDTO= authenticateService.authenticate(request.getUserCode(),request.getSystemUserType());
          SimpleUserInfoResponse response =  authenticateFaceConverter.convertToSimpleUserInfoResponse(simpleUserInfoDTO);
          log.info("execute authenticate info,req:{},result:{}", JSON.toJSONString(request),JSON.toJSONString(response));
          return Result.success(response);
     }
}
