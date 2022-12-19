package com.salute.mall.auth.api.client;

import com.salute.mall.auth.api.fallback.AuthenticateClientFallback;
import com.salute.mall.auth.api.request.MallAuthenticateRequest;
import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.common.core.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mall-auth", contextId = "saluteAuthenticateClient", fallbackFactory = AuthenticateClientFallback.class)
public interface AuthenticateClient {

    Result<SimpleUserInfoResponse> authenticate(@RequestBody MallAuthenticateRequest request);
}
