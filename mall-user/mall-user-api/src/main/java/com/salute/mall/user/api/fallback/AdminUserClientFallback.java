package com.salute.mall.user.api.fallback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.user.api.client.AdminUserClient;
import com.salute.mall.user.api.response.AdminUserSimpleResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Slf4j
public class AdminUserClientFallback implements FallbackFactory<AdminUserClient> {

    @Override
    public AdminUserClient create(Throwable throwable) {
        return new AdminUserClient() {
            @Override
            public Result<AdminUserSimpleResponse> getSimpleUserInfo(@NotBlank String userCode) {
                log.error("execute  getSimpleUserInfo error,request:{}", JSON.toJSONString(userCode),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
