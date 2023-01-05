package com.salute.mall.pay.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.pay.service.mapper.PayChannelConfigMapper;
import com.salute.mall.pay.service.pojo.entity.PayChannelConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayChannelConfigRepository {

    @Autowired
    private PayChannelConfigMapper payChannelConfigMapper;

    public PayChannelConfig getBySystemSourceAndAppId(String systemSource, String appId) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(systemSource,appId),"参数异常");
        LambdaQueryWrapper<PayChannelConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayChannelConfig::getSystemSource,systemSource)
                    .eq(PayChannelConfig::getAppId,appId);
       return payChannelConfigMapper.selectOne(queryWrapper);
    }

}
