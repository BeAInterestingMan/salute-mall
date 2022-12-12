package com.salute.mall.marketing.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.marketing.service.mapper.MarketingCouponStockMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponStock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    public MarketingCouponStock getByCouponActivityCodeTE(String couponActivityCode) {
        SaluteAssertUtil.isTrue(StringUtils.isBlank(couponActivityCode),"参数错误");
        LambdaQueryWrapper<MarketingCouponStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MarketingCouponStock::getCouponActivityCode,couponActivityCode);
        MarketingCouponStock couponStock = marketingCouponStockMapper.selectOne(queryWrapper);
        SaluteAssertUtil.isTrue(Objects.nonNull(couponStock),couponActivityCode+"优惠券库存不存在");
        return couponStock;
    }

    /**
     * @Description 更新优惠券库存数量
     * @author liuhu
     * @param canReceiveNum
     * @param couponActivityCode
     * @date 2022/12/12 15:32
     * @return int
     */
    public int updateCouponNum(int canReceiveNum, String couponActivityCode) {
        return marketingCouponStockMapper.updateCouponNum(canReceiveNum,couponActivityCode);
    }
}
