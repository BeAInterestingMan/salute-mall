package com.salute.mall.marketing.service.repository;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.datasource.helper.MybatisBatchHelper;
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
import java.util.Objects;

@Slf4j
@Component
public class MarketingCouponUserRecordRepository {

    @Autowired
    private MarketingCouponUserRecordMapper marketingCouponUserRecordMapper;

    @Autowired
    private MybatisBatchHelper mybatisBatchHelper;

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
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(userCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MarketingCouponUserRecord::getUserCode,userCode)
                .eq(MarketingCouponUserRecord::getStatus, CouponUserRecordStatusEnum.RECEIVED.getValue())
                .le(MarketingCouponUserRecord::getStartTime,new Date())
                .ge(MarketingCouponUserRecord::getEndTime,new Date());
        return marketingCouponUserRecordMapper.selectList(queryWrapper);
    }

    /**
     * @Description 获取用户在优惠券活动的已领券实例
     * @author liuhu
     * @param couponActivityCode
     * @param userCode
     * @date 2022/12/12 15:04
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    public List<MarketingCouponUserRecord> queryByActivityCodeAndUserCode(String couponActivityCode, String userCode) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(couponActivityCode,userCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(MarketingCouponUserRecord::getCouponActivityCode,couponActivityCode)
                .eq(MarketingCouponUserRecord::getUserCode,userCode);
        return marketingCouponUserRecordMapper.selectList(queryWrapper);
    }

    public void batchInsert(List<MarketingCouponUserRecord> couponUserRecordList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(couponUserRecordList),"参数异常");
        mybatisBatchHelper.batchInsertOrUpdate(couponUserRecordList,MarketingCouponUserRecordMapper.class,(record,mapper)->mapper.insert(record));
    }

    /**
     * @Description 业务单号查询
     * @author liuhu
     * @param couponCode
     * @param bizCode
     * @date 2023/1/3 17:04
     * @return com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord
     */
    public MarketingCouponUserRecord getByCouponCodeAndBizCode(String couponCode, String bizCode) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(couponCode,bizCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(MarketingCouponUserRecord::getCouponCode,couponCode)
                .eq(MarketingCouponUserRecord::getBizCode,bizCode);
        return marketingCouponUserRecordMapper.selectOne(queryWrapper);
    }

    public void updateByCouponCode(MarketingCouponUserRecord update) {
        SaluteAssertUtil.isTrue(Objects.nonNull(update) && StringUtils.isNotBlank(update.getCouponCode()),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(MarketingCouponUserRecord::getCouponCode,update.getCouponCode());
        int rows = marketingCouponUserRecordMapper.update(update, queryWrapper);
        if(rows != 1){
            log.info("归还优惠券失败,req:{}", JSON.toJSONString(update));
            throw new BusinessException("500","归还优惠券失败");
        }
    }

    public MarketingCouponUserRecord getByBizCode(String bizCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(bizCode),"参数异常");
        LambdaQueryWrapper<MarketingCouponUserRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(MarketingCouponUserRecord::getBizCode,bizCode);
        return marketingCouponUserRecordMapper.selectOne(queryWrapper);
    }
}
