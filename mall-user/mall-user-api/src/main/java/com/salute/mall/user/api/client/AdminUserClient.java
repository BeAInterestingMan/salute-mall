package com.salute.mall.user.api.client;


import com.salute.mall.common.core.entity.Result;
import com.salute.mall.user.api.fallback.AdminUserClientFallback;
import com.salute.mall.user.api.response.AdminUserSimpleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;

@FeignClient(value = "mall-user", contextId = "saluteAdminUserClient", fallbackFactory = AdminUserClientFallback.class)
public interface AdminUserClient {

    @GetMapping("user/adminUser/getSimpleUserInfo")
    Result<AdminUserSimpleResponse> getSimpleUserInfo(@NotBlank String userCode);
}
