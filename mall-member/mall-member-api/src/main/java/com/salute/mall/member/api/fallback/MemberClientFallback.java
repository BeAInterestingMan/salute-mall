package com.salute.mall.member.api.fallback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.member.api.client.MemberClient;
import com.salute.mall.member.api.response.MemberSimpleInfoResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Slf4j
public class MemberClientFallback implements FallbackFactory<MemberClient> {


    @Override
    public MemberClient create(Throwable throwable) {
        return new MemberClient() {
            @Override
            public Result<MemberSimpleInfoResponse> getMemberSimpleInfoByMemberCode(@NotBlank String memberCode) {
                log.error("execute ProductSearchClient getMemberSimpleInfoByMemberCode error,request:{}", JSON.toJSONString(memberCode),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
