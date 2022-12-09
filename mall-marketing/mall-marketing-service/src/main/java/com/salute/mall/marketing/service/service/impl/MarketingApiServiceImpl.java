package com.salute.mall.marketing.service.service.impl;

import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderServiceDTO;
import com.salute.mall.marketing.service.pojo.dto.UseCouponServiceDTO;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord;
import com.salute.mall.marketing.service.repository.MarketingCouponUserRecordRepository;
import com.salute.mall.marketing.service.service.MarketingApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MarketingApiServiceImpl implements MarketingApiService {

    @Autowired
    private MarketingCouponUserRecordRepository recordRepository;

    @Autowired
    private RedisHelper redisHelper;

    /**
     * @Description 使用优惠券
     * @author liuhu
     * @param dto
     * @date 2022/12/9 15:30
     * @return void
     */
    public void useCoupon(UseCouponServiceDTO dto) {
        String key = RedisConstants.LockKey.SHOPPING_MARKETING_USE_COUPON+dto.getCouponCode();
        Boolean block = redisHelper.set(key, "BLOCK", 3L, TimeUnit.SECONDS);
        SaluteAssertUtil.isTrue(Objects.equals(block,Boolean.TRUE),"请勿重试点击");
        MarketingCouponUserRecord userRecord = recordRepository.getByCouponCode(dto.getCouponCode());
        SaluteAssertUtil.isTrue(Objects.nonNull(userRecord),"优惠券不存在");
        recordRepository.updateStatus(dto);
    }

    public void prepareOrder(PrepareOrderServiceDTO dto) {
        //1.如果传入优惠券号 则判断是否可用
        if(StringUtils.isNotBlank(dto.getCouponCode())){
            return;
        }
        //2.如果未传入优惠券号  则默认推荐一张面值最大的优惠券

    }
}
