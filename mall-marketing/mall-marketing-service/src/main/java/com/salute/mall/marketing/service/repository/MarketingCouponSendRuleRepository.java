package com.salute.mall.marketing.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.marketing.service.mapper.MarketingCouponSendRuleMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponSendRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MarketingCouponSendRuleRepository {

    @Autowired
    private MarketingCouponSendRuleMapper couponSendRuleMapper;

    public MarketingCouponSendRule getByActivityCode(String couponActivityCode, String userCode) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(couponActivityCode,userCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponSendRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MarketingCouponSendRule::getCouponActivityCode,couponActivityCode);
        return couponSendRuleMapper.selectOne(queryWrapper);
    }
}
