package com.salute.mall.marketing.service.service.impl;

import com.google.common.collect.Lists;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.marketing.service.converter.MarketingApiServiceConverter;
import com.salute.mall.marketing.service.core.CouponCore;
import com.salute.mall.marketing.service.enums.CouponUserRecordStatusEnum;
import com.salute.mall.marketing.service.pojo.context.OrderContext;
import com.salute.mall.marketing.service.pojo.context.OrderDetailContext;
import com.salute.mall.marketing.service.pojo.dto.*;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord;
import com.salute.mall.marketing.service.repository.MarketingCouponUserRecordRepository;
import com.salute.mall.marketing.service.service.MarketingApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CouponCore couponCore;

    @Autowired
    private MarketingApiServiceConverter marketingApiServiceConverter;

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
        recordRepository.updateStatus(dto);
    }


    @Override
    public PrepareOrderDTO prepareOrder(PrepareOrderServiceDTO dto) {
        //1.如果传入优惠券号 则判断是否可用
        List<MarketingCouponUserRecord> userCouponList = queryUserAvailableCouponList(dto);
        //2.构建订单上下文
        OrderContext orderContext = buildOrderContext(dto);
        //3.获取用户可用优惠券
        List<AvailableCouponInfoDTO> availableCouponInfoDTOS = couponCore.queryAvailableCouponInstanceInOrderContext(userCouponList, orderContext);
        if(CollectionUtils.isEmpty(availableCouponInfoDTOS)){
            log.info("当前用户无可用优惠券，userCode:{}",dto.getUserCode());
            return null;
        }
        // 4.获得可用和不可用优惠券编号 用于用户切换
        List<String> availableCouponCodeList = availableCouponInfoDTOS.stream().map(AvailableCouponInfoDTO::getCouponCode).collect(Collectors.toList());
        List<String> notAvailableCouponCodeList = userCouponList.stream()
                .filter(userRecord -> !availableCouponCodeList.contains(userRecord.getCouponCode())).map(MarketingCouponUserRecord::getCouponCode).collect(Collectors.toList());
        // 5.计算出可用优惠券 对于订单的优惠金额
        List<AvailableCouponDiscountInfoDTO> discountInfoDTOS = couponCore.queryCouponInstanceDiscountInOrderContext(availableCouponInfoDTOS, orderContext);
        AvailableCouponDiscountInfoDTO infoDTO = discountInfoDTOS.stream()
                .max(Comparator.comparing(AvailableCouponDiscountInfoDTO::getCouponDiscountAmount))
                .orElseGet(null);
        // 6.构造数据返回
        PrepareOrderDTO prepareOrderDTO =  marketingApiServiceConverter.convertToPrepareOrderDTO(infoDTO);
        prepareOrderDTO.setAvailableCouponCodeList(availableCouponCodeList);
        prepareOrderDTO.setNotAvailableCouponCodeList(notAvailableCouponCodeList);
        return prepareOrderDTO;
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
        }else {
            // 获取用户所有的优惠券再推荐
           return recordRepository.queryByUseCodeAndStatus(dto.getUserCode());
        }
    }

    /**
     * @Description 构建订单上下文
     * @author liuhu
     * @param dto
     * @date 2022/12/9 20:53
     * @return com.salute.mall.marketing.service.pojo.context.OrderContext
     */
    private OrderContext buildOrderContext(PrepareOrderServiceDTO dto) {
        OrderContext orderContext = new OrderContext();
        orderContext.setUserCode(dto.getUserCode());
        orderContext.setUserName(dto.getUserName());
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