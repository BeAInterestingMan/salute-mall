package com.salute.mall.order.service.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.order.service.core.CreateOrderCore;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderDTO;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderResultDTO;
import com.salute.mall.order.service.repository.SaleOrderDetailRepository;
import com.salute.mall.order.service.repository.SaleOrderRepository;
import com.salute.mall.order.service.service.SaleOrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SaleOrderServiceImpl implements SaleOrderService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CreateOrderCore createOrderCore;

    @Autowired
    private SaleOrderDetailRepository saleOrderDetailRepository;

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Override
    public String getSaleOrderCode() {
        return IdUtil.getSnowflake().nextIdStr();
    }

    @Override
    public CreateSaleOrderResultDTO createSaleOrder(CreateSaleOrderDTO dto) {
        String key  = RedisConstants.CartLockKey.SHOPPING_CREATE_SALE_ORDER+dto.getSaleOrderCode();
        RLock lock = redissonClient.getLock(key);
        try {
            //500ms尝试获取锁  获取锁后2s内自动释放锁
            boolean lockFlag = lock.tryLock(500, 2000, TimeUnit.MILLISECONDS);
            if(!lockFlag){
                throw new BusinessException("500","手速太快了，请稍后操作");
            }
           return createOrderCore.doCreateSaleOrder(dto);
        } catch (InterruptedException e) {
            log.error("创建订单获取锁失败,req:{}", JSON.toJSONString(dto));
            throw new BusinessException("500","创建订单获取锁异常");
        }catch (Exception e){
            log.error("创建订单获取锁失败,req:{}", JSON.toJSONString(dto));
            throw new BusinessException("500","创建订单异常");
        }finally {
            // 判断当前线程已加锁 如果把trylock放在try外面则不需要判断  redission自动通过线程ID判断是否时本线程释放锁
            if(lock.isLocked()&& lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }


}
