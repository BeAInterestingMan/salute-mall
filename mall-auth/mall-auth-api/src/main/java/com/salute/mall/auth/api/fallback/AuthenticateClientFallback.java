package com.salute.mall.auth.api.fallback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.api.client.BaseAuthInfoClient;
import com.salute.mall.auth.api.request.MallAuthenticationRequest;
import com.salute.mall.auth.api.request.MallAuthorizationRequest;
import com.salute.mall.auth.api.response.SimpleUserInfoResponse;
import com.salute.mall.common.core.entity.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticateClientFallback implements FallbackFactory<BaseAuthInfoClient> {

    @Override
    public BaseAuthInfoClient create(Throwable throwable) {
        return new BaseAuthInfoClient() {
            @Override
            public Result<SimpleUserInfoResponse> authentication(MallAuthenticationRequest request) {
                log.error("execute  authenticate error,request:{}", JSON.toJSONString(request),throwable);
                return Result.error("500","服务繁忙");
            }

            @Override
            public Result<Void> authorization(MallAuthorizationRequest request) {
                log.error("execute  authenticate error,request:{}", JSON.toJSONString(request),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
