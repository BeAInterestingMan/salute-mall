package com.salute.mall.auth.api.client;

import com.salute.mall.auth.api.fallback.AuthenticateClientFallback;
import com.salute.mall.auth.api.request.MallAuthenticationRequest;
import com.salute.mall.auth.api.request.MallAuthorizationRequest;
import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.common.core.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mall-auth", contextId = "saluteAuthenticateClient", fallbackFactory = AuthenticateClientFallback.class)
public interface BaseAuthInfoClient {

    @ApiOperation("认证")
    @PostMapping("auth/base/authentication")
    Result<SimpleUserInfoResponse> authentication(@RequestBody MallAuthenticationRequest request);

    @ApiOperation("授权")
    @PostMapping("auth/base/authorization")
    Result<Void> authorization(@RequestBody MallAuthorizationRequest request);
}
