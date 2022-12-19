package com.salute.mall.auth.service.adapt;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.service.adapt.converter.MemberAdaptConverter;
import com.salute.mall.auth.service.adapt.dto.MemberSimpleInfoDTO;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.member.api.client.MemberClient;
import com.salute.mall.member.api.response.MemberSimpleInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class MemberAdapt {

    @Autowired
    private MemberClient memberClient;

    @Autowired
    private MemberAdaptConverter memberAdaptConverter;

    public MemberSimpleInfoDTO getMemberSimpleInfoByMemberCode(String memberCode){
        log.info("execute getMemberSimpleInfoByMemberCode info,req:{}", JSON.toJSONString(memberCode));
        Result<MemberSimpleInfoResponse> result = memberClient.getMemberSimpleInfoByMemberCode(memberCode);
        if(Objects.isNull(result) || Objects.equals(result.isSuccess(),Boolean.FALSE)){
            log.error("execute searchProduct info,req:{},resp:{}", JSON.toJSONString(memberCode),JSON.toJSONString(result));
            return null;
        }
        MemberSimpleInfoResponse data = result.getResult();
        log.info("execute searchProduct success,req:{},resp:{}",JSON.toJSONString(data),JSON.toJSONString(result));
        return  memberAdaptConverter.convertToMemberSimpleInfoDTO(data);
    }
}
