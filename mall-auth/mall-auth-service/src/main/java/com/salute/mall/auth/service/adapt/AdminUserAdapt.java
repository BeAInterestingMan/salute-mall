package com.salute.mall.auth.service.adapt;

import com.alibaba.fastjson.JSON;
import com.salute.mall.auth.service.adapt.converter.AdminUserAdaptConverter;
import com.salute.mall.auth.service.adapt.dto.AdminUserSimpleInfoDTO;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.user.api.client.AdminUserClient;
import com.salute.mall.user.api.response.AdminUserSimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class AdminUserAdapt {

    @Autowired
    private AdminUserClient adminUserClient;

    @Autowired
    private AdminUserAdaptConverter adminUserAdaptConverter;

    public AdminUserSimpleInfoDTO getAdminUserSimpleInfoByUserCode(String userCode){
        log.info("execute getAdminUserSimpleInfoByUserCode info,req:{}", JSON.toJSONString(userCode));
        Result<AdminUserSimpleResponse> result = adminUserClient.getSimpleUserInfo(userCode);
        if(Objects.isNull(result) || Objects.equals(result.isSuccess(),Boolean.FALSE)){
            log.error("execute getAdminUserSimpleInfoByUserCode info,req:{},resp:{}", JSON.toJSONString(userCode),JSON.toJSONString(result));
            return null;
        }
        AdminUserSimpleResponse data = result.getResult();
        log.info("execute getAdminUserSimpleInfoByUserCode success,req:{},resp:{}",JSON.toJSONString(data),JSON.toJSONString(result));
        return  adminUserAdaptConverter.convertToAdminUserSimpleInfoDTO(data);
    }
}
