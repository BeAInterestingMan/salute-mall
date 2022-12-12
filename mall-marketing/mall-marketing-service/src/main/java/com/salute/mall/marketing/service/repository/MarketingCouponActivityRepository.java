package com.salute.mall.marketing.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.marketing.service.enums.CouponActivityStatusEnum;
import com.salute.mall.marketing.service.mapper.MarketingCouponActivityMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponActivity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class MarketingCouponActivityRepository {

    @Autowired
    private MarketingCouponActivityMapper couponActivityMapper;

    /**
     * @Description 查询门店生效的活动集合
     * @author liuhu
     * @param shopCode
     * @date 2022/12/12 11:05
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponActivity>
     */
    public List<MarketingCouponActivity> queryShopAvailableCouponList(String shopCode) {
        SaluteAssertUtil.isTrue(StringUtils.isBlank(shopCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MarketingCouponActivity::getShopCode,shopCode)
                .eq(MarketingCouponActivity::getStatus, CouponActivityStatusEnum.RUNNING.getValue())
                .le(MarketingCouponActivity::getStartTime,new Date())
                .ge(MarketingCouponActivity::getEndTime,new Date());
        return couponActivityMapper.selectList(queryWrapper);
    }

    public MarketingCouponActivity getByCouponActivityCodeTE(String couponActivityCode) {
        SaluteAssertUtil.isTrue(StringUtils.isBlank(couponActivityCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MarketingCouponActivity::getCouponActivityCode,couponActivityCode)
                .eq(MarketingCouponActivity::getStatus, CouponActivityStatusEnum.RUNNING.getValue())
                .le(MarketingCouponActivity::getStartTime,new Date())
                .ge(MarketingCouponActivity::getEndTime,new Date());
        MarketingCouponActivity couponActivity = couponActivityMapper.selectOne(queryWrapper);
        SaluteAssertUtil.isTrue(Objects.nonNull(couponActivity),couponActivityCode+"优惠券活动不存在");
        return couponActivity;
    }
}
