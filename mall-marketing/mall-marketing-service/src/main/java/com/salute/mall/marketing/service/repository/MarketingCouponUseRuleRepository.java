package com.salute.mall.marketing.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.marketing.service.mapper.MarketingCouponUseRuleMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponStock;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUseRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MarketingCouponUseRuleRepository {

    @Autowired
    private MarketingCouponUseRuleMapper couponUseRuleMapper;

    public List<MarketingCouponUseRule> queryByActivityCodeList(List<String> couponActivityCodeList) {
        if(CollectionUtils.isEmpty(couponActivityCodeList)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<MarketingCouponUseRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MarketingCouponUseRule::getCouponActivityCode,couponActivityCodeList);
        return couponUseRuleMapper.selectList(queryWrapper);
    }
}
