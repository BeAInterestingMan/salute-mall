package com.salute.mall.marketing.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.marketing.service.mapper.MarketingCouponUseRuleDetailMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUseRuleDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MarketingCouponUseRuleDetailRepository {

    @Autowired
    private MarketingCouponUseRuleDetailMapper couponUseRuleDetailMapper;

    public List<MarketingCouponUseRuleDetail> queryByActivityCodeList(List<String> couponActivityCodeList) {
        if(CollectionUtils.isEmpty(couponActivityCodeList)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<MarketingCouponUseRuleDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MarketingCouponUseRuleDetail::getCouponActivityCode,couponActivityCodeList);
        return couponUseRuleDetailMapper.selectList(queryWrapper);
    }
}
