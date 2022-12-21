package com.salute.mall.auth.service.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.api.request.MallAuthenticationRequest;
import com.salute.mall.auth.api.request.MallAuthorizationRequest;
import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.auth.service.converter.AuthenticateFaceConverter;
import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import com.salute.mall.auth.service.service.AuthenticateService;
import com.salute.mall.common.core.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/base/")
@Slf4j
@Api(tags = "认证授权控制器")
public class BaseAuthController {

     @Autowired
     private AuthenticateService authenticateService;

     @Autowired
     private AuthenticateFaceConverter authenticateFaceConverter;

     @ApiOperation("认证")
     @PostMapping("authentication")
     public Result<SimpleUserInfoResponse> authentication(@RequestBody MallAuthenticationRequest request){
          log.info("execute authenticate info,req:{}", JSON.toJSONString(request));
          SimpleUserInfoDTO simpleUserInfoDTO= authenticateService.authenticate(request.getAccessToken());
          SimpleUserInfoResponse response =  authenticateFaceConverter.convertToSimpleUserInfoResponse(simpleUserInfoDTO);
          log.info("execute authenticate info,req:{},result:{}", JSON.toJSONString(request),JSON.toJSONString(response));
          return Result.success(response);
     }

     @ApiOperation("授权")
     @PostMapping("authorization")
     public Result<Void> authorization(@RequestBody MallAuthorizationRequest request){
          log.info("execute authorization info,req:{}", JSON.toJSONString(request));
          authenticateService.authorization(request.getUserCode(),request.getUri());
          return Result.success();
     }

}
