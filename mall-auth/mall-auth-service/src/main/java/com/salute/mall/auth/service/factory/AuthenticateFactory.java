package com.salute.mall.auth.service.factory;

import com.salute.mall.auth.service.strategy.AuthenticateStrategy;
import com.salute.mall.auth.service.enums.SystemUserTypeEnum;
import com.salute.mall.common.core.utils.ApplicationContextUtil;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class AuthenticateFactory {

    /**
     * @Description 获取认证的实现类
     * @author liuhu
     * @param systemUserType
     * @date 2022/12/19 16:50
     * @return com.salute.mall.auth.service.strategy.AuthenticateStrategy
     */
    public AuthenticateStrategy getInstance(String systemUserType){
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(systemUserType),"参数异常");
        SystemUserTypeEnum userTypeEnum = SystemUserTypeEnum.getByValue(systemUserType);
        SaluteAssertUtil.isTrue(Objects.nonNull(userTypeEnum),"当前用户类型不支持认证");
        return (AuthenticateStrategy)ApplicationContextUtil.getBean(userTypeEnum.getBeanName());
    }
}
