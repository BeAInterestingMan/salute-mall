package com.salute.mall.member.api.client;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.member.api.fallback.MemberClientFallback;
import com.salute.mall.member.api.response.MemberSimpleInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotBlank;
@FeignClient(value = "mall-member", contextId = "saluteMemberClient", fallbackFactory = MemberClientFallback.class)
public interface MemberClient {

    @PostMapping("/member/getByMemberCode")
    Result<MemberSimpleInfoResponse> getMemberSimpleInfoByMemberCode(@NotBlank String memberCode);
}
