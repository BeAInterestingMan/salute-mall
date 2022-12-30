package com.salute.mall.marketing.service.core;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.marketing.service.enums.CouponUseRuleCouponTypeEnum;
import com.salute.mall.marketing.service.enums.CouponUserRecordUseTypeEnum;
import com.salute.mall.marketing.service.enums.PreferentialTypeEnum;
import com.salute.mall.marketing.service.pojo.context.OrderContext;
import com.salute.mall.marketing.service.pojo.context.OrderDetailContext;
import com.salute.mall.marketing.service.pojo.context.ProductContext;
import com.salute.mall.marketing.service.pojo.dto.*;
import com.salute.mall.marketing.service.pojo.dto.discount.SubmitOrderResultDTO;
import com.salute.mall.marketing.service.pojo.dto.discount.SubmitOrderSkuResultDTO;
import com.salute.mall.marketing.service.pojo.entity.*;
import com.salute.mall.marketing.service.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CouponMatchCore {

    @Autowired
    private MarketingCouponUserRecordRepository couponUserRecordRepository;

    @Autowired
    private MarketingCouponStockRepository couponStockRepository;

    @Autowired
    private MarketingCouponUseRuleRepository couponUseRuleRepository;

    @Autowired
    private MarketingCouponUseRuleDetailRepository couponUseRuleDetailRepository;

    @Autowired
    private MarketingPreferentialRecordRepository marketingPreferentialRecordRepository;


    /**
     * @Description 订单上下文中获取可用优惠券实例
     * @author liuhu
     * @param couponUserRecordList
     * @param orderContext
     * @date 2022/12/9 16:45
     * @return void
     */
    public List<AvailableCouponInfoDTO> queryAvailableCouponInstanceInOrderContext(List<MarketingCouponUserRecord> couponUserRecordList,
                                                                                 OrderContext orderContext){
        //1.获取优惠券实例
        if(CollectionUtils.isEmpty(couponUserRecordList) || Objects.isNull(orderContext)){
            return Lists.newArrayList();
        }
        List<String> couponActivityCodeList = couponUserRecordList.stream().map(MarketingCouponUserRecord::getCouponActivityCode).distinct().collect(Collectors.toList());
        //2.过滤有效券实例
        List<MarketingCouponUserRecord> couponInstanceAvailableRecordList = filerCouponInstanceAvailable(couponUserRecordList);
        //3.过滤库存有效
        List<MarketingCouponUserRecord> stockAvailableRecordList = filerMatchUserRecordStockLimit(couponInstanceAvailableRecordList, couponActivityCodeList);
        //4.过滤使用规则符合
        return filterMatchSendRule(stockAvailableRecordList,orderContext);

    }

    /**
     * @Description 计算优惠券的优惠金额
     * @author liuhu
     * @param availableCouponInfoDTOS
     * @date 2022/12/10 13:06
     * @return java.util.List<com.salute.mall.marketing.service.pojo.dto.AvailableCouponDiscountInfoDTO>
     */
    public List<AvailableCouponDiscountInfoDTO> queryCouponInstanceDiscountInOrderContext(List<AvailableCouponInfoDTO> availableCouponInfoDTOS,
                                                                                          BigDecimal orderOriginTotalAmount){
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(availableCouponInfoDTOS) && Objects.nonNull(orderOriginTotalAmount),"参数异常");
        //1.订单原始总金额
       return  availableCouponInfoDTOS.stream().map(infoDTO -> {
             AvailableCouponDiscountInfoDTO dto = new AvailableCouponDiscountInfoDTO();
             //2.可用优惠券的商品满足总金额
             BigDecimal productTotalAmount = infoDTO.getAvailableSkuList().stream().map(v -> new BigDecimal(v.getBuyCount()).multiply(v.getSalePrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
             //3.如果可优惠的商品金额大于优惠券的面值 则优惠金额未优惠券的面值 否则商品金额
             BigDecimal orderDiscountTotalAmount = productTotalAmount.compareTo(infoDTO.getCouponAmount())>0?infoDTO.getCouponAmount():productTotalAmount;
             dto.setCouponCode(infoDTO.getCouponCode());
             dto.setCouponAmount(infoDTO.getCouponAmount());
             dto.setCouponPreferentialAmount(orderDiscountTotalAmount);
             dto.setOrderAmount(orderDiscountTotalAmount);
             dto.setOrderFinalAmount(orderOriginTotalAmount.subtract(orderDiscountTotalAmount));
             return dto;
         }).collect(Collectors.toList());
    }

    /**
     * @Description 使用规则符合
     * @author liuhu
     * @param marketingCouponUserRecordList
     * @param orderContext
     * @date 2022/12/9 20:32
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    private List<AvailableCouponInfoDTO> filterMatchSendRule(List<MarketingCouponUserRecord> marketingCouponUserRecordList,
                                                             OrderContext orderContext) {
        // 可用实例的优惠券活动编号
        List<String> couponActivityCodeList = marketingCouponUserRecordList.stream().map(MarketingCouponUserRecord::getCouponActivityCode).distinct().collect(Collectors.toList());
        List<MarketingCouponUseRule> couponUseRules = couponUseRuleRepository.queryByActivityCodeList(couponActivityCodeList);
        Map<String, MarketingCouponUseRule> useRuleMap = couponUseRules.stream().collect(Collectors.toMap(MarketingCouponUseRule::getCouponActivityCode, Function.identity(), (k1, k2) -> k1));
        List<MarketingCouponUseRuleDetail> couponUseRuleDetails = couponUseRuleDetailRepository.queryByActivityCodeList(couponActivityCodeList);
        Map<String, List<MarketingCouponUseRuleDetail>> useRuleDetailMap = couponUseRuleDetails.stream().collect(Collectors.groupingBy(MarketingCouponUseRuleDetail::getCouponActivityCode));
        return  marketingCouponUserRecordList.stream()
                .map(userRecord ->{
                    // 1.获取当前券实例的使用规则
                    MarketingCouponUseRule couponUseRule = useRuleMap.get(userRecord.getCouponActivityCode());
                    // 2.获取当前券实例的使用规则详情
                    List<MarketingCouponUseRuleDetail> useRuleDetailList = useRuleDetailMap.get(userRecord.getCouponActivityCode());
                    // 3.构建可用优惠券详情
                    return buildAvailableCouponInfoDTO(userRecord,couponUseRule,useRuleDetailList,orderContext);
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * @Description 规则匹配
     * @author liuhu
     * @param couponUseRule
     * @param useRuleDetailList
     * @param orderContext
     * @date 2022/12/9 20:33
     * @return AvailableCouponInfoDTO
     */
    private AvailableCouponInfoDTO buildAvailableCouponInfoDTO(
                                  MarketingCouponUserRecord userRecord,
                                  MarketingCouponUseRule couponUseRule,
                                  List<MarketingCouponUseRuleDetail> useRuleDetailList,
                                  OrderContext orderContext) {
        CouponUserRecordUseTypeEnum typeEnum = CouponUserRecordUseTypeEnum.getByValue(couponUseRule.getUseType());
        if(Objects.isNull(typeEnum)){
            return null;
        }
        switch (typeEnum){
            case ALL_PRODUCT:
                return matchAllProduct(userRecord,couponUseRule,orderContext);
            case CATEGORY:
                return matchCategory(userRecord,couponUseRule,useRuleDetailList,orderContext);
            case PRODUCT:
                return matchProduct(userRecord,couponUseRule,useRuleDetailList,orderContext);
            default:
                log.info("当前优惠券使用规则类型未配置,rule:{}", JSON.toJSONString(couponUseRule));
                break;
        }
        return null;
    }

    /**
     * @Description 指定商品使用 当前订单匹配
     * @author liuhu
     * @param couponUseRule
     * @param useRuleDetailList
     * @param orderContext
     * @date 2022/12/9 20:31
     * @return AvailableCouponInfoDTO
     */
    private AvailableCouponInfoDTO matchProduct(MarketingCouponUserRecord userRecord,
                                                MarketingCouponUseRule couponUseRule,
                                                List<MarketingCouponUseRuleDetail> useRuleDetailList,
                                                OrderContext orderContext) {
        Set<String> skuSet = useRuleDetailList.stream().map(MarketingCouponUseRuleDetail::getSpecificCode).collect(Collectors.toSet());
        // 购买商品的分类集合 至少包含了一个设置的指定分类
        List<OrderDetailContext> matchSkuProductList = orderContext.getOrderDetailContextList().stream()
                .filter(product -> skuSet.contains(product.getSkuCode()))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(matchSkuProductList)){
            log.info("当前订单使用优惠券-因指定商品不满足使用，couponActivityCode:{},userCode:{}",couponUseRule.getCouponActivityCode(),orderContext.getUserCode());
            return null;
        }
        // 满足分类的商品合计金额
        BigDecimal matchSkuCodeProductTotalAmount = matchSkuProductList.stream().map(v -> new BigDecimal(v.getBuyQty()).multiply(v.getSalePrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        boolean typeMatch = matchCouponType(couponUseRule, matchSkuCodeProductTotalAmount);
        if(!typeMatch){
            log.info("当前订单使用优惠券-因优惠券门槛金额不满足使用，couponActivityCode:{},userCode:{}",couponUseRule.getCouponActivityCode(),orderContext.getUserCode());
            return null;
        }
        // 满足的商品
        List<AvailableCouponDetailInfoDTO> infoDTOS = buildAvailableCouponDetailInfoDTOList(matchSkuProductList);
        return AvailableCouponInfoDTO.builder()
                .couponCode(userRecord.getCouponCode())
                .couponType(couponUseRule.getCouponType())
                .couponFullAmount(couponUseRule.getCouponFullAmount())
                .useType(couponUseRule.getUseType())
                .availableSkuList(infoDTOS)
                .build();
    }

    /**
     * @Description 指定分类使用 当前订单匹配
     * @author liuhu
     * @param couponUseRule
     * @param useRuleDetailList
     * @param orderContext
     * @date 2022/12/9 20:31
     * @return AvailableCouponInfoDTO
     */
    private AvailableCouponInfoDTO matchCategory(MarketingCouponUserRecord userRecord,
                                                 MarketingCouponUseRule couponUseRule,
                                                 List<MarketingCouponUseRuleDetail> useRuleDetailList,
                                                 OrderContext orderContext) {
        Set<String> categorySet = useRuleDetailList.stream().map(MarketingCouponUseRuleDetail::getSpecificCode).collect(Collectors.toSet());
        // 购买商品的分类集合 至少包含了一个设置的指定分类
        List<OrderDetailContext> matchCategoryProductList = orderContext.getOrderDetailContextList().stream()
                .filter(product -> categorySet.contains(product.getCategoryCodeThird()))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(matchCategoryProductList)){
            log.info("当前订单使用优惠券-因指定分类不满足使用，couponActivityCode:{},userCode:{}",couponUseRule.getCouponActivityCode(),orderContext.getUserCode());
            return null;
        }
        // 满足分类的商品合计金额
        BigDecimal matchCategoryProductTotalAmount = matchCategoryProductList.stream().map(v -> new BigDecimal(v.getBuyQty()).multiply(v.getSalePrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        boolean typeMatch = matchCouponType(couponUseRule, matchCategoryProductTotalAmount);
        if(!typeMatch){
            log.info("当前订单使用优惠券-因优惠券门槛金额不满足使用，couponActivityCode:{},userCode:{}",couponUseRule.getCouponActivityCode(),orderContext.getUserCode());
            return null;
        }
        // 满足的商品
        List<AvailableCouponDetailInfoDTO> infoDTOS = buildAvailableCouponDetailInfoDTOList(matchCategoryProductList);
        return AvailableCouponInfoDTO.builder()
                .couponCode(userRecord.getCouponCode())
                .couponType(couponUseRule.getCouponType())
                .couponFullAmount(couponUseRule.getCouponFullAmount())
                .useType(couponUseRule.getUseType())
                .availableSkuList(infoDTOS)
                .build();
    }

    /**
     * @Description 全部商品匹配
     * @author liuhu
     * @param couponUseRule
     * @param orderContext
     * @date 2022/12/9 20:10
     * @return boolean
     */
    private AvailableCouponInfoDTO matchAllProduct(MarketingCouponUserRecord userRecord,MarketingCouponUseRule couponUseRule, OrderContext orderContext) {
        BigDecimal totalProductAmount = orderContext.getOrderDetailContextList().stream().map(OrderDetailContext::getSalePrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 匹配门槛金额
        boolean typeMatch = matchCouponType(couponUseRule, totalProductAmount);
        if(!typeMatch){
            return null;
        }
        // 满足的商品
        List<AvailableCouponDetailInfoDTO> infoDTOS = buildAvailableCouponDetailInfoDTOList(orderContext.getOrderDetailContextList());
        return AvailableCouponInfoDTO.builder()
                .couponCode(userRecord.getCouponCode())
                .couponType(couponUseRule.getCouponType())
                .couponFullAmount(couponUseRule.getCouponFullAmount())
                .useType(couponUseRule.getUseType())
                .availableSkuList(infoDTOS)
                .build();
    }

   /**
    * @Description 构建当前优惠券可用的商品信息
    * @author liuhu
    * @param orderDetailContextList
    * @date 2022/12/10 12:33
    * @return java.util.List<com.salute.mall.marketing.service.pojo.dto.AvailableCouponDetailInfoDTO>
    */
   private  List<AvailableCouponDetailInfoDTO> buildAvailableCouponDetailInfoDTOList(List<OrderDetailContext> orderDetailContextList){
       return orderDetailContextList.stream().map(v -> {
            AvailableCouponDetailInfoDTO dto = new AvailableCouponDetailInfoDTO();
            dto.setSkuCode(v.getSkuCode());
            dto.setBuyCount(v.getBuyQty());
            dto.setSalePrice(v.getSalePrice());
            return dto;
        }).collect(Collectors.toList());
    }


    /**
     * @Description 优惠券类型满足
     * @author liuhu
     * @param couponUseRule
     * @param orderAmount
     * @date 2022/12/9 20:24
     * @return boolean
     */
    private boolean matchCouponType(MarketingCouponUseRule couponUseRule, BigDecimal orderAmount) {
        // 无门槛 直接匹配
        if(Objects.equals(couponUseRule.getCouponType(), CouponUseRuleCouponTypeEnum.ALL.getValue())){
            return true;
        }
        // 指定金额满足
        if(Objects.equals(couponUseRule.getCouponType(), CouponUseRuleCouponTypeEnum.AMOUNT_SATISFY.getValue())){
            return orderAmount.compareTo(couponUseRule.getCouponFullAmount()) > 0;
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
    private List<MarketingCouponUserRecord> filerMatchUserRecordStockLimit(List<MarketingCouponUserRecord> couponUserRecordList,
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

    /**
     * @Description 过滤优惠券活动库存不满足的
     * @author liuhu
     * @param marketingCouponActivityList
     * @param couponActivityCodeList
     * @date 2022/12/12 11:09
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponActivity>
     */
    private List<MarketingCouponActivity> filerMatchCouponActivityStockAvailable(List<MarketingCouponActivity> marketingCouponActivityList,
                                                               List<String> couponActivityCodeList) {

        List<MarketingCouponStock> couponStockList = couponStockRepository.queryByActivityCodeList(couponActivityCodeList);
        List<String> stockNotAvailableCouponActivityCodeList = couponStockList.stream()
                .filter(couponStock -> couponStock.getAvailableStock() <= 0)
                .map(MarketingCouponStock::getCouponActivityCode)
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(stockNotAvailableCouponActivityCodeList)){
            return marketingCouponActivityList;
        }
        return marketingCouponActivityList.stream()
                .filter(couponActivity->stockNotAvailableCouponActivityCodeList.contains(couponActivity.getCouponActivityCode()))
                .collect(Collectors.toList());
    }

    /**
     * @Description 商品维度获取可领的优惠券活动
     * @author liuhu
     * @param couponActivityList
     * @param productContext
     * @date 2022/12/12 13:42
     * @return void
     */
    public List<MarketingCouponActivity> queryAvailableCouponInstanceInProductContext(List<MarketingCouponActivity> couponActivityList,
                                                             ProductContext productContext) {
        List<String> couponActivityCodeList = couponActivityList.stream().map(MarketingCouponActivity::getCouponActivityCode).collect(Collectors.toList());
        //1.过滤库存有效
        List<MarketingCouponActivity> stockAvailableRecordList = filerMatchCouponActivityStockAvailable(couponActivityList,couponActivityCodeList);
        //2.过滤规则不满足的
        return filerMatchCouponActivitySendRuleAvailable(stockAvailableRecordList, productContext);
    }

    /**
     * @Description 过滤出商品可用的优惠券活动
     * @author liuhu
     * @param couponActivityList
     * @param productContext
     * @date 2022/12/12 13:57
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponActivity>
     */
    private List<MarketingCouponActivity> filerMatchCouponActivitySendRuleAvailable(List<MarketingCouponActivity> couponActivityList, ProductContext productContext) {
        List<String> couponActivityCodeList = couponActivityList.stream().map(MarketingCouponActivity::getCouponActivityCode).distinct().collect(Collectors.toList());
        List<MarketingCouponUseRule> couponUseRules = couponUseRuleRepository.queryByActivityCodeList(couponActivityCodeList);
        Map<String, MarketingCouponUseRule> useRuleMap = couponUseRules.stream().collect(Collectors.toMap(MarketingCouponUseRule::getCouponActivityCode, Function.identity(), (k1, k2) -> k1));
        List<MarketingCouponUseRuleDetail> couponUseRuleDetails = couponUseRuleDetailRepository.queryByActivityCodeList(couponActivityCodeList);
        Map<String, List<MarketingCouponUseRuleDetail>> useRuleDetailMap = couponUseRuleDetails.stream().collect(Collectors.groupingBy(MarketingCouponUseRuleDetail::getCouponActivityCode));
        return  couponActivityList.stream().filter(couponActivity->{
            // 1.获取当前券实例的使用规则
            MarketingCouponUseRule couponUseRule = useRuleMap.get(couponActivity.getCouponActivityCode());
            // 2.获取当前券实例的使用规则详情
            List<MarketingCouponUseRuleDetail> useRuleDetailList = useRuleDetailMap.get(couponActivity.getCouponActivityCode());
            // 3.匹配
            return matchProductCoupon(couponUseRule,useRuleDetailList,productContext);
        }).collect(Collectors.toList());
    }


    public boolean matchProductCoupon(MarketingCouponUseRule couponUseRule,
                                      List<MarketingCouponUseRuleDetail> useRuleDetailList,
                                      ProductContext productContext){
        CouponUserRecordUseTypeEnum typeEnum = CouponUserRecordUseTypeEnum.getByValue(couponUseRule.getUseType());
        if(Objects.isNull(typeEnum)){
            return false;
        }
        switch (typeEnum){
            case ALL_PRODUCT:
                return true;
            case CATEGORY:
                return matchActivityCategory(useRuleDetailList,productContext);
            case PRODUCT:
                return matchActivityProduct(useRuleDetailList,productContext);
            default:
                log.info("当前优惠券使用规则类型未配置,rule:{}", JSON.toJSONString(couponUseRule));
                break;
        }
        return false;
    }

    /**
     * @Description 指定商品
     * @author liuhu
     * @param useRuleDetailList
     * @param productContext
     * @date 2022/12/12 13:55
     * @return boolean
     */
    private boolean matchActivityProduct(List<MarketingCouponUseRuleDetail> useRuleDetailList,
                                         ProductContext productContext) {
        Set<String> skuCodeSet = useRuleDetailList.stream().map(MarketingCouponUseRuleDetail::getSpecificCode).collect(Collectors.toSet());
        return skuCodeSet.contains(productContext.getCategoryCodeThird());
    }

    /**
     * @Description 指定分类
     * @author liuhu
     * @param useRuleDetailList
     * @param productContext
     * @date 2022/12/12 13:55
     * @return boolean
     */
    private boolean matchActivityCategory(List<MarketingCouponUseRuleDetail> useRuleDetailList,
                                          ProductContext productContext) {
        Set<String> categorySet = useRuleDetailList.stream().map(MarketingCouponUseRuleDetail::getSpecificCode).collect(Collectors.toSet());
        return categorySet.contains(productContext.getCategoryCodeThird());
    }
}
