package com.salute.mall.user.api.client;


import com.salute.mall.common.core.entity.Result;
import com.salute.mall.user.api.fallback.AdminPermissionClientFallback;
import com.salute.mall.user.api.response.UserPermissionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;

@FeignClient(value = "mall-user", contextId = "saluteAdminPermissionClient", fallbackFactory = AdminPermissionClientFallback.class)
public interface AdminPermissionClient {

    @GetMapping("getUserPermission")
    Result<UserPermissionResponse> getUserPermission(@NotBlank String userCode);
}
