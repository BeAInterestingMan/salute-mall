package com.salute.mall.marketing.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.marketing.service.mapper.MarketingCouponStockMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponStock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MarketingCouponStockRepository {

    @Autowired
    private MarketingCouponStockMapper marketingCouponStockMapper;

    public List<MarketingCouponStock> queryByActivityCodeList(List<String> couponActivityCodeList) {
        if(CollectionUtils.isEmpty(couponActivityCodeList)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<MarketingCouponStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MarketingCouponStock::getCouponActivityCode,couponActivityCodeList);
        return marketingCouponStockMapper.selectList(queryWrapper);
    }
}
