package com.salute.mall.marketing.service.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.marketing.service.converter.MarketingApiServiceConverter;
import com.salute.mall.marketing.service.core.CouponMatchCore;
import com.salute.mall.marketing.service.core.PreferentialCalculateCore;
import com.salute.mall.marketing.service.enums.CouponActivityStatusEnum;
import com.salute.mall.marketing.service.enums.CouponUserRecordStatusEnum;
import com.salute.mall.marketing.service.pojo.context.OrderContext;
import com.salute.mall.marketing.service.pojo.context.OrderDetailContext;
import com.salute.mall.marketing.service.pojo.context.ProductContext;
import com.salute.mall.marketing.service.pojo.dto.*;
import com.salute.mall.marketing.service.pojo.dto.discount.SubmitOrderResultDTO;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponActivity;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponSendRule;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponStock;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord;
import com.salute.mall.marketing.service.repository.MarketingCouponActivityRepository;
import com.salute.mall.marketing.service.repository.MarketingCouponSendRuleRepository;
import com.salute.mall.marketing.service.repository.MarketingCouponStockRepository;
import com.salute.mall.marketing.service.repository.MarketingCouponUserRecordRepository;
import com.salute.mall.marketing.service.service.MarketingApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MarketingApiServiceImpl implements MarketingApiService {

    @Autowired
    private MarketingCouponUserRecordRepository recordRepository;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private CouponMatchCore couponMatchCore;

    @Autowired
    private MarketingCouponActivityRepository marketingCouponActivityRepository;

    @Autowired
    private MarketingCouponStockRepository marketingCouponStockRepository;

    @Autowired
    private MarketingCouponUserRecordRepository userRecordRepository;

    @Autowired
    private MarketingCouponSendRuleRepository couponSendRuleRepository;

    @Autowired
    private MarketingApiServiceConverter marketingApiServiceConverter;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private PreferentialCalculateCore preferentialCalculateCore;

    /**
     * @Description 使用优惠券
     * @author liuhu
     * @param dto
     * @date 2022/12/9 15:30
     * @return void
     */
    @Override
    public void useCoupon(UseCouponServiceDTO dto) {
        String key = RedisConstants.LockKey.SHOPPING_MARKETING_USE_COUPON+dto.getCouponCode();
        Boolean block = redisHelper.set(key, "BLOCK", 3L, TimeUnit.SECONDS);
        SaluteAssertUtil.isTrue(Objects.equals(block,Boolean.TRUE),"请勿重试点击");
        MarketingCouponUserRecord userRecord = recordRepository.getByCouponCode(dto.getCouponCode());
        SaluteAssertUtil.isTrue(Objects.nonNull(userRecord),"优惠券不存在");
        SaluteAssertUtil.isTrue(!Objects.equals(userRecord.getStatus(), CouponUserRecordStatusEnum.RECEIVED.getValue()),"优惠券已失效");
        recordRepository.updateStatus(dto);
    }


    @Override
    public PrepareOrderDTO prepareOrder(PrepareOrderServiceDTO dto) {
        //1.如果传入优惠券号 则判断是否可用
        List<MarketingCouponUserRecord> userCouponList = queryUserAvailableCouponList(dto);
        //2.构建订单上下文
        OrderContext orderContext = buildOrderContext(dto);
        //3.获取用户可用优惠券
        List<AvailableCouponInfoDTO> availableCouponInfoDTOS = couponMatchCore.queryAvailableCouponInstanceInOrderContext(userCouponList, orderContext);
        if(CollectionUtils.isEmpty(availableCouponInfoDTOS)){
            return PrepareOrderDTO.builder()
                    .couponCode(dto.getCouponCode())
                    .orderOriginAmount(orderContext.getOrderOriginAmount())
                    .couponAmount(BigDecimal.ZERO)
                    .couponDiscountAmount(BigDecimal.ZERO)
                    .orderFinalAmount(BigDecimal.ZERO)
                    .availableCouponCodeList(Lists.newArrayList())
                    .notAvailableCouponCodeList(Lists.newArrayList())
                    .build();
        }
        // 4.获得可用和不可用优惠券编号 用于用户切换
        List<String> availableCouponCodeList = availableCouponInfoDTOS.stream().map(AvailableCouponInfoDTO::getCouponCode).collect(Collectors.toList());
        List<String> notAvailableCouponCodeList = userCouponList.stream()
                .map(MarketingCouponUserRecord::getCouponCode)
                .filter(s -> !availableCouponCodeList.contains(s)).collect(Collectors.toList());
        // 5.计算出可用优惠券 对于订单的优惠金额
        List<AvailableCouponDiscountInfoDTO> discountInfoDTOS = preferentialCalculateCore.queryCouponInstanceDiscountInOrderContext(availableCouponInfoDTOS, orderContext.getOrderOriginAmount());
        // 获得最优惠的优惠券
        AvailableCouponDiscountInfoDTO infoDTO = discountInfoDTOS.stream()
                .max(Comparator.comparing(AvailableCouponDiscountInfoDTO::getCouponPreferentialAmount))
                .orElse(null);
        // 6.构造数据返回
        PrepareOrderDTO prepareOrderDTO =  marketingApiServiceConverter.convertToPrepareOrderDTO(infoDTO);
        prepareOrderDTO.setAvailableCouponCodeList(availableCouponCodeList);
        prepareOrderDTO.setNotAvailableCouponCodeList(notAvailableCouponCodeList);
        return prepareOrderDTO;
    }


    @Override
    public SubmitOrderResultDTO submitOrder(SubmitOrderDTO dto) {
        String key = RedisConstants.LockKey.SHOPPING_MARKETING_SUBMIT_ORDER+dto.getOrderCode();
        Boolean block = redisHelper.set(key, "BLOCK", 1000L, TimeUnit.MILLISECONDS);
        SaluteAssertUtil.isTrue(Objects.equals(block,Boolean.TRUE),"请勿重复点击");
        //1.判断当前优惠券是否已被使用
        MarketingCouponUserRecord userRecord = recordRepository.getByCouponCode(dto.getCouponCode());
        SaluteAssertUtil.isTrue(Objects.nonNull(userRecord),"优惠券不存在");
        SaluteAssertUtil.isTrue(!Objects.equals(userRecord.getStatus(), CouponUserRecordStatusEnum.RECEIVED.getValue()),"优惠券已失效");
        //2.构建订单上下文
        OrderContext orderContext = buildSubmitOrderContext(dto);
        //3.判断优惠券是否可用
        List<AvailableCouponInfoDTO> availableCouponInfoDTOS = couponMatchCore.queryAvailableCouponInstanceInOrderContext(Lists.newArrayList(userRecord), orderContext);
        SaluteAssertUtil.isTrue(CollectionUtils.size(availableCouponInfoDTOS) ==1,"当前订单不可使用优惠券"+dto.getOrderCode()+"_"+dto.getCouponCode());
        //3.计算分摊
        return preferentialCalculateCore.calculationApportionment(availableCouponInfoDTOS.get(0),orderContext);
    }

    private OrderContext buildSubmitOrderContext(SubmitOrderDTO dto) {
        BigDecimal orderAmount = dto.getDetailList().stream().map(v -> new BigDecimal(v.getBugQty()).multiply(v.getSalePrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        OrderContext orderContext = new OrderContext();
        orderContext.setUserCode(dto.getUserCode());
        orderContext.setUserName(dto.getUserName());
        orderContext.setOrderOriginAmount(orderAmount);
        List<OrderDetailContext> detailContexts = dto.getDetailList().stream().map(detail -> {
            OrderDetailContext orderDetailContext = new OrderDetailContext();
            orderDetailContext.setBuyQty(detail.getBugQty());
            orderDetailContext.setSkuCode(detail.getSkuCode());
            orderDetailContext.setSkuName(detail.getSkuName());
            orderDetailContext.setCategoryCodeThird(detail.getCategoryCodeThird());
            orderDetailContext.setSalePrice(detail.getSalePrice());
            return orderDetailContext;
        }).collect(Collectors.toList());
        orderContext.setOrderDetailContextList(detailContexts);
        return orderContext;
    }

    @Override
    public List<ProductCouponInfoDTO> queryProductCouponInfo(ProductCouponInfoServiceDTO dto) {
        List<MarketingCouponActivity> couponActivities = marketingCouponActivityRepository.queryShopAvailableCouponList(dto.getShopCode());
        ProductContext productContext = marketingApiServiceConverter.convertToProductContext(dto);
        List<MarketingCouponActivity> couponActivityList = couponMatchCore.queryAvailableCouponInstanceInProductContext(couponActivities, productContext);
        return marketingApiServiceConverter.convertToProductCouponInfoDTOList(couponActivityList);
    }

    @Override
    public void receiveCoupon(ReceiveCouponDTO dto) {
        MarketingCouponActivity couponActivity = marketingCouponActivityRepository.getByCouponActivityCodeTE(dto.getCouponActivityCode());
        SaluteAssertUtil.isTrue(!Objects.equals(couponActivity.getStatus(), CouponActivityStatusEnum.RUNNING.getValue()),"优惠券活动已失效");
        String key = RedisConstants.LockKey.SHOPPING_MARKETING_SEND_COUPON+couponActivity.getCouponActivityCode();
        RLock rLock = redissonClient.getLock(key);
        try {
            boolean lock = rLock.tryLock(500, 1000L, TimeUnit.MILLISECONDS);
            if(!lock){
                throw new BusinessException("500","请稍后重试");
            }
            MarketingCouponSendRule couponSendRule = couponSendRuleRepository.getByActivityCode(dto.getCouponActivityCode(), dto.getUserCode());
            MarketingCouponStock marketingCouponStock = marketingCouponStockRepository.getByCouponActivityCodeTE(dto.getCouponActivityCode());
            //1.校验库存  2可领是否达到上线 3.获取可领数量
            int canReceiveNum = getUserCanReceiveNum(dto, couponSendRule, marketingCouponStock);
            //2.保存数据
            saveReceiveCouponInfo(canReceiveNum,dto,couponActivity);
        } catch (InterruptedException e) {
            log.error("领券未获取到锁,req:{}", JSON.toJSONString(dto),e);
            throw new BusinessException("500","请稍后重试");
        }catch (BusinessException e){
            throw new BusinessException(e.getCode(),e.getMsg());
        }catch (Exception e){
            log.error("领券异常,req:{}", JSON.toJSONString(dto),e);
            throw new BusinessException("500","领券异常");
        }finally {
            if(rLock.isLocked() && rLock.isHeldByCurrentThread()){
                rLock.unlock();
            }
        }
    }

    /**
     * @Description 保存数据
     * @author liuhu
     * @param canReceiveNum
     * @param dto
     * @param couponActivity
     * @date 2022/12/12 15:41
     * @return void
     */
    private void saveReceiveCouponInfo(int canReceiveNum,
                                       ReceiveCouponDTO dto,
                                       MarketingCouponActivity couponActivity) {
        List<MarketingCouponUserRecord> couponUserRecordList = buildCouponUserRecordList(canReceiveNum, couponActivity, dto);
        transactionTemplate.execute(ts->{
            //1.更新库存
            int rows = marketingCouponStockRepository.updateCouponNum(canReceiveNum, couponActivity.getCouponActivityCode());
            if(rows != 1){
                ts.setRollbackOnly();
            }
            //2.保存领券记录
            recordRepository.batchInsert(couponUserRecordList);
            return null;
        });

    }

    private List<MarketingCouponUserRecord> buildCouponUserRecordList(int canReceiveNum,
                                           MarketingCouponActivity couponActivity,
                                           ReceiveCouponDTO dto) {
        List<MarketingCouponUserRecord> list = new ArrayList<>();
        for (int i = 0; i < canReceiveNum; i++) {
            MarketingCouponUserRecord record =  marketingApiServiceConverter.convertToMarketingCouponUserRecord(couponActivity);
            record.setId(null);
            record.setUserCode(dto.getUserCode());
            record.setUserName(dto.getUserName());
            record.setCreator(dto.getUserCode());
            record.setCreatorCode(dto.getUserCode());
            record.setCouponCode(IdUtil.getSnowflake().nextIdStr());
            list.add(record);
        }
        return list;
    }


    private int getUserCanReceiveNum(ReceiveCouponDTO dto,
                                           MarketingCouponSendRule couponSendRule,
                                           MarketingCouponStock marketingCouponStock) {
       SaluteAssertUtil.isTrue(marketingCouponStock.getAvailableStock()<0,"优惠券库存不足");
       Integer limitQty = couponSendRule.getParticipateLimitQty();
       //判断是否达到领券上限
       List<MarketingCouponUserRecord> userRecords = userRecordRepository.queryByActivityCodeAndUserCode(dto.getCouponActivityCode(), dto.getUserCode());
       int usedCouponNum = CollectionUtils.size(userRecords);
       // 如果已发放的优惠券+本次发放的优惠券>限制发放的优惠券数量  则提示
       SaluteAssertUtil.isTrue(limitQty>=usedCouponNum+couponSendRule.getSendQty(),"优惠券已达到领取上线");
       Integer receiveLimit =limitQty-(usedCouponNum+couponSendRule.getSendQty());
       //获取可发放优惠券数量
       return Math.max(marketingCouponStock.getAvailableStock() - receiveLimit, 0);
    }


    /**
     * @Description 获取用户的优惠券实例信息 如果CouponCode不为空 则为用户手动切换券
     * @author liuhu
     * @param dto
     * @date 2022/12/10 13:10
     * @return java.util.List<com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord>
     */
    private List<MarketingCouponUserRecord> queryUserAvailableCouponList(PrepareOrderServiceDTO dto) {
        // 指定优惠券
        if(StringUtils.isNotBlank(dto.getCouponCode())){
            MarketingCouponUserRecord userRecord = recordRepository.getByCouponCode(dto.getCouponCode());
            SaluteAssertUtil.isTrue(Objects.nonNull(userRecord), dto.getCouponCode()+"当前优惠券不存在");
            return Lists.newArrayList();
        }
        // 获取用户所有的优惠券再推荐
        return recordRepository.queryByUseCodeAndStatus(dto.getUserCode());
    }

    /**
     * @Description 构建订单上下文
     * @author liuhu
     * @param dto
     * @date 2022/12/9 20:53
     * @return com.salute.mall.marketing.service.pojo.context.OrderContext
     */
    private OrderContext buildOrderContext(PrepareOrderServiceDTO dto) {
        BigDecimal orderAmount = dto.getDetailList().stream().map(v -> new BigDecimal(v.getBugQty()).multiply(v.getSalePrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        OrderContext orderContext = new OrderContext();
        orderContext.setUserCode(dto.getUserCode());
        orderContext.setUserName(dto.getUserName());
        orderContext.setOrderOriginAmount(orderAmount);
        List<OrderDetailContext> detailContexts = dto.getDetailList().stream().map(detail -> {
            OrderDetailContext orderDetailContext = new OrderDetailContext();
            orderDetailContext.setBuyQty(detail.getBugQty());
            orderDetailContext.setSkuCode(detail.getSkuCode());
            orderDetailContext.setSkuName(detail.getSkuName());
            orderDetailContext.setCategoryCodeThird(detail.getCategoryCodeThird());
            orderDetailContext.setSalePrice(detail.getSalePrice());
            return orderDetailContext;
        }).collect(Collectors.toList());
        orderContext.setOrderDetailContextList(detailContexts);
        return orderContext;
    }
}
