package com.salute.mall.marketing.service.core;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.marketing.service.enums.CouponUserRecordStatusEnum;
import com.salute.mall.marketing.service.enums.CouponUserRecordUseTypeEnum;
import com.salute.mall.marketing.service.pojo.context.OrderContext;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponStock;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUseRule;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUseRuleDetail;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord;
import com.salute.mall.marketing.service.repository.MarketingCouponStockRepository;
import com.salute.mall.marketing.service.repository.MarketingCouponUseRuleDetailRepository;
import com.salute.mall.marketing.service.repository.MarketingCouponUseRuleRepository;
import com.salute.mall.marketing.service.repository.MarketingCouponUserRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CouponCore {

    @Autowired
    private MarketingCouponUserRecordRepository couponUserRecordRepository;

    @Autowired
    private MarketingCouponStockRepository couponStockRepository;

    @Autowired
    private MarketingCouponUseRuleRepository couponUseRuleRepository;

    @Autowired
    private MarketingCouponUseRuleDetailRepository couponUseRuleDetailRepository;

    /**
     * @Description 订单上下文中获取可用优惠券
     * @author liuhu
     * @param couponCodeList
     * @param orderContext
     * @date 2022/12/9 16:45
     * @return void
     */
    public List<MarketingCouponUserRecord> queryAvailableCouponInOrderContext(List<String> couponCodeList, OrderContext orderContext){
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(couponCodeList)&& Objects.nonNull(orderContext),"参数异常");
        //1.获取优惠券实例
        List<MarketingCouponUserRecord> couponUserRecordList = couponUserRecordRepository.queryByStatusAndCouponCodeList(couponCodeList, CouponUserRecordStatusEnum.RECEIVED.getValue());
        if(CollectionUtils.isEmpty(couponUserRecordList)){
            return Lists.newArrayList();
        }
        List<String> couponActivityCodeList = couponUserRecordList.stream().map(MarketingCouponUserRecord::getCouponActivityCode).distinct().collect(Collectors.toList());
        //2.过滤有效券实例
        List<MarketingCouponUserRecord> couponInstanceAvailableRecordList = filerCouponInstanceAvailable(couponUserRecordList);
        //3.过滤库存有效
        List<MarketingCouponUserRecord> stockAvailableRecordList = filerMatchStockLimit(couponInstanceAvailableRecordList, couponActivityCodeList);
        //4.过滤使用规则符合
        return filterMatchSendRule(stockAvailableRecordList,couponActivityCodeList);

    }

    private void filterMatchSendRule(List<MarketingCouponUserRecord> stockAvailableRecordList,
                                    List<String> couponActivityCodeList) {
        List<MarketingCouponUseRule> couponUseRules = couponUseRuleRepository.queryByActivityCodeList(couponActivityCodeList);
        List<MarketingCouponUseRuleDetail> couponUseRuleDetails = couponUseRuleDetailRepository.queryByActivityCodeList(couponActivityCodeList);
        Map<String, MarketingCouponUseRuleDetail> useRuleDetailMap = couponUseRuleDetails.stream().collect(Collectors.toMap(MarketingCouponUseRuleDetail::getCouponActivityCode, Function.identity(), (k1, k2) -> k1));
        couponUseRules.stream().filter(couponUseRule->{
            MarketingCouponUseRuleDetail useRuleDetail = useRuleDetailMap.get(couponUseRule.getCouponActivityCode());
           return matchSendRule(couponUseRule,useRuleDetail);
        }).collect(Collectors.toList());
    }

    private boolean matchSendRule(MarketingCouponUseRule couponUseRule,
                                  MarketingCouponUseRuleDetail useRuleDetail) {
        CouponUserRecordUseTypeEnum typeEnum = CouponUserRecordUseTypeEnum.getByValue(couponUseRule.getUseType());
        if(Objects.isNull(typeEnum)){
            return false;
        }
        return false;
    }


    /**
     * @Description 过滤券实例不满足
     * @author liuhu
     * @param couponUserRecordList
     * @date 2022/12/9 17:55
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    private List<MarketingCouponUserRecord> filerCouponInstanceAvailable(List<MarketingCouponUserRecord> couponUserRecordList) {
       return couponUserRecordList.stream().filter(couponUserRecord->{
            LocalDateTime startTime = LocalDateTimeUtil.of(couponUserRecord.getStartTime());
            LocalDateTime endTime = LocalDateTimeUtil.of(couponUserRecord.getEndTime());
            LocalDateTime now = LocalDateTime.now();
            // 过滤时间不满足
            if (!(startTime.isBefore(now) && endTime.isAfter(now))) {
                log.info("当前优惠券实例已过有效期，couponCode:{}",couponUserRecord.getCouponCode());
                  return false;
            }
            return true;
        }).collect(Collectors.toList());
    }

    /**
     * @Description  过滤库存不足的优惠券
     * @author liuhu
     * @param couponUserRecordList
     * @param couponActivityCodeList
     * @date 2022/12/9 17:28
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    private List<MarketingCouponUserRecord> filerMatchStockLimit(List<MarketingCouponUserRecord> couponUserRecordList,
                                         List<String> couponActivityCodeList) {
        List<MarketingCouponStock> couponStockList = couponStockRepository.queryByActivityCodeList(couponActivityCodeList);
        List<String> stockNotAvailableCouponActivityCodeList = couponStockList.stream()
                    .filter(couponStock -> couponStock.getAvailableStock() <= 0)
                    .map(MarketingCouponStock::getCouponActivityCode)
                    .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(stockNotAvailableCouponActivityCodeList)){
            return couponUserRecordList;
        }
        return couponUserRecordList.stream()
                .filter(couponUserRecord->stockNotAvailableCouponActivityCodeList.contains(couponUserRecord.getCouponActivityCode()))
                .collect(Collectors.toList());
    }
}
