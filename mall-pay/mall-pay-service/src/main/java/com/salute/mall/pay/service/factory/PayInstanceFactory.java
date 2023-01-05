package com.salute.mall.pay.service.factory;

import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.pay.service.enums.PayTypeEnum;
import com.salute.mall.pay.service.strategy.PayStrategyService;
import com.salute.mall.pay.service.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class PayInstanceFactory {

    /**
     * @Description 从工厂中获取Bean
     * @author liuhu
     * @param payType
     * @date 2023/1/5 17:06
     * @return com.salute.mall.pay.service.strategy.PayStrategyService
     */
    public PayStrategyService getPayStrategyService(String payType){
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(payType),"参数异常");
        PayTypeEnum typeEnum = PayTypeEnum.getBeanByValue(payType);
        SaluteAssertUtil.isTrue(Objects.nonNull(typeEnum) && StringUtils.isNotBlank(typeEnum.getBeanName()) ,"当前支付类型不支持");
        return (PayStrategyService) ApplicationContextUtil.getBean(typeEnum.getBeanName());
    }
}
