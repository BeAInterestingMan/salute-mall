package com.salute.mall.product.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.product.service.converter.ProductStockServiceConverter;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDaoDTO;
import com.salute.mall.product.service.repository.ProductStockRepository;
import com.salute.mall.product.service.service.ProductStockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ProductStockServiceImpl implements ProductStockService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private ProductStockServiceConverter productStockServiceConverter;

    @Override
    public void operateFreezeStock(OperateFreezeStockDTO dto) {
        // 订单单号防重锁  控制重复请求
        String key  = RedisConstants.LockKey.SHOPPING_OPERATE_STOCK+dto.getBizCode();
        RLock lock = redissonClient.getLock(key);
        try {
            //500ms尝试获取锁  获取锁后2s内自动释放锁
            boolean lockFlag = lock.tryLock(500, 2000, TimeUnit.MILLISECONDS);
            if(!lockFlag){
                throw new BusinessException("500","手速太快了，请稍后操作");
            }
            doOperateFreezeStock(dto);
        } catch (InterruptedException e) {
            log.error("操作库存获取锁异常,req:{}", JSON.toJSONString(dto));
            throw new BusinessException("500","操作库存获取锁异常");
        }catch (Exception e){
            log.error("操作库存异常,req:{}", JSON.toJSONString(dto));
            throw new BusinessException("500","操作库存异常");
        }finally {
            // 判断当前线程已加锁 如果把trylock放在try外面则不需要判断  redission自动通过线程ID判断是否时本线程释放锁
            if(lock.isLocked()&& lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    private void doOperateFreezeStock(OperateFreezeStockDTO dto) {
      OperateFreezeStockDaoDTO daoDTO =  productStockServiceConverter.convertToOperateFreezeStockDaoDTO(dto);
      transactionTemplate.execute(ts->{
         int rows =  productStockRepository.doOperateFreezeStock(daoDTO);
      })
    }


}
