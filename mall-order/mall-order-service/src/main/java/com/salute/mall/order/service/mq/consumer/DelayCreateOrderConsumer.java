package com.salute.mall.order.service.mq.consumer;

import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.order.service.core.CancelSaleOrderCore;
import com.salute.mall.order.service.enums.CancelTypeEnum;
import com.salute.mall.order.service.pojo.dto.CancelOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *  @Description 超时未支付自动取消
 *  @author liuhu
 *  @Date 2023/1/3 10:34
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "delayCreateOrderConsumer",topic = "DELAY_ORDER")
public class DelayCreateOrderConsumer implements RocketMQListener<String> {

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private CancelSaleOrderCore cancelSaleOrderCore;

    @Override
    public void onMessage(String message) {
        log.info("execute delayCreateOrderConsumer info,message:{}", message);
        if (StringUtils.isBlank(message)) {
            return;
        }
        //防重
        String key = RedisConstants.SaleOrderLockKey.SHOPPING_DELAY_CANCEL_SALE_ORDER + message;
        Boolean block = redisHelper.setNx(key, "BLOCK", 10000L, TimeUnit.MILLISECONDS);
        if (Objects.equals(block, Boolean.FALSE)) {
            log.info("延迟消费创建订单未获取到锁，saleOrderCode:{}", message);
            return;
        }
        CancelOrderDTO dto = CancelOrderDTO.builder()
                .cancelType(CancelTypeEnum.DELAY_CANCEL.getValue())
                .saleOrderCode(message)
                .reason("").operateCode("SYSTEM")
                .operateCode("SYSTEM")
                .build();
        cancelSaleOrderCore.delayCancel(dto);
    }

}
