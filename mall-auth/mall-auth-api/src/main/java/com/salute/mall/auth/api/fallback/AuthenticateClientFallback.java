package com.salute.mall.auth.api.fallback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.api.client.AuthenticateClient;
import com.salute.mall.auth.api.request.MallAuthenticateRequest;
import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.common.core.entity.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticateClientFallback implements FallbackFactory<AuthenticateClient> {

    @Override
    public AuthenticateClient create(Throwable throwable) {
        return new AuthenticateClient() {
            @Override
            public Result<SimpleUserInfoResponse> authenticate(MallAuthenticateRequest request) {
                log.error("execute  authenticate error,request:{}", JSON.toJSONString(request),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
