package com.salute.mall.auth.service.strategy.impl;

import com.salute.mall.auth.service.adapt.MemberAdapt;
import com.salute.mall.auth.service.adapt.dto.MemberSimpleInfoDTO;
import com.salute.mall.auth.service.converter.AuthenticateStrategyConverter;
import com.salute.mall.auth.service.dto.SimpleUserInfoDTO;
import com.salute.mall.auth.service.enums.SystemUserTypeEnum;
import com.salute.mall.auth.service.strategy.AuthenticateStrategy;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 *  @Description pc用户认证策略实现类
 *  @author liuhu
 *  @Date 2022/12/19 18:33
 */
@Component
@Slf4j
public class AdminAuthenticateStrategy implements AuthenticateStrategy {

    @Autowired
    private MemberAdapt memberAdapt;

    @Autowired
    private AuthenticateStrategyConverter strategyConverter;


    @Override
    public SimpleUserInfoDTO doAuthenticate(String userCode, String systemUserType) {
        // TODO 是否需要加缓存
        SaluteAssertUtil.isTrue(Objects.equals(systemUserType, SystemUserTypeEnum.ADMIN.getValue()),"用户类型异常");
        MemberSimpleInfoDTO memberSimpleInfo = memberAdapt.getMemberSimpleInfoByMemberCode(userCode);
        if(Objects.isNull(memberSimpleInfo)){
            log.info("为查询到当前会员信息,userCode:{}",userCode);
            return null;
        }
        return strategyConverter.convertToSimpleUserInfoDTO(userCode);
    }
}
