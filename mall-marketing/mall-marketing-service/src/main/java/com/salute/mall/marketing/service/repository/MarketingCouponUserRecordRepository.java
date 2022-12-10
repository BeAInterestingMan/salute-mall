package com.salute.mall.marketing.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.marketing.service.enums.CouponUserRecordStatusEnum;
import com.salute.mall.marketing.service.mapper.MarketingCouponUserRecordMapper;
import com.salute.mall.marketing.service.pojo.dto.UseCouponServiceDTO;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class MarketingCouponUserRecordRepository {

    @Autowired
    private MarketingCouponUserRecordMapper marketingCouponUserRecordMapper;

    public MarketingCouponUserRecord getByCouponCode(String couponCode) {
        if(StringUtils.isNotBlank(couponCode)){
            return null;
        }
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MarketingCouponUserRecord::getCouponCode,couponCode);
       return marketingCouponUserRecordMapper.selectOne(queryWrapper);
    }

    /**
     * @Description 更新优惠券状态
     * @author liuhu
     * @param dto
     * @date 2022/12/9 17:09
     * @return void
     */
    public void updateStatus(UseCouponServiceDTO dto) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(dto.getCouponCode(),dto.getCouponCode(),dto.getBizCode()),"参数异常");
        LambdaUpdateWrapper<MarketingCouponUserRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(MarketingCouponUserRecord::getCouponCode,dto.getCouponCode())
                     .eq(MarketingCouponUserRecord::getBizCode,dto.getBizCode())
                     .eq(MarketingCouponUserRecord::getStatus, CouponUserRecordStatusEnum.RECEIVED.getValue())
                     .le(MarketingCouponUserRecord::getStartTime,new Date())
                     .ge(MarketingCouponUserRecord::getEndTime,new Date());
        MarketingCouponUserRecord record = new MarketingCouponUserRecord();
        record.setCouponCode(dto.getCouponCode());
        record.setStatus(CouponUserRecordStatusEnum.USED.getValue());
        record.setUsingTime(new Date());
        record.setModifier(dto.getOperateCode());
        record.setModifierCode(dto.getOperateCode());
        int update = marketingCouponUserRecordMapper.update(record, updateWrapper);
        SaluteAssertUtil.isTrue(update == 1,"更新优惠券使用状态异常");
    }

    /**
     * @Description 获取可用优惠券
     * @author liuhu
     * @param couponCodeList
     * @date 2022/12/9 17:09
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    public List<MarketingCouponUserRecord> queryByStatusAndCouponCodeList(List<String> couponCodeList,String status) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(couponCodeList),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MarketingCouponUserRecord::getCouponCode,couponCodeList)
                .eq(MarketingCouponUserRecord::getStatus, status);
        return marketingCouponUserRecordMapper.selectList(queryWrapper);
    }

    /**
     * @Description 获取可用优惠券
     * @author liuhu
     * @param couponCodeList
     * @date 2022/12/9 17:09
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    public List<MarketingCouponUserRecord> queryEnableCouponCodeList(List<String> couponCodeList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(couponCodeList),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MarketingCouponUserRecord::getCouponCode,couponCodeList)
                     .eq(MarketingCouponUserRecord::getStatus, CouponUserRecordStatusEnum.RECEIVED.getValue())
                     .le(MarketingCouponUserRecord::getStartTime,new Date())
                     .ge(MarketingCouponUserRecord::getEndTime,new Date());
        return marketingCouponUserRecordMapper.selectList(queryWrapper);
    }

    /**
     * @Description 查询用户有效的优惠券
     * @author liuhu
     * @param userCode
     * @date 2022/12/9 20:43
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    public List<MarketingCouponUserRecord> queryByUseCodeAndStatus(String userCode) {
        SaluteAssertUtil.isTrue(StringUtils.isAnyBlank(userCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MarketingCouponUserRecord::getUserCode,userCode)
                .eq(MarketingCouponUserRecord::getStatus, CouponUserRecordStatusEnum.RECEIVED.getValue())
                .le(MarketingCouponUserRecord::getStartTime,new Date())
                .ge(MarketingCouponUserRecord::getEndTime,new Date());
        return marketingCouponUserRecordMapper.selectList(queryWrapper);
    }
}
