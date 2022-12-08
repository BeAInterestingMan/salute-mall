package com.salute.mall.product.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.constants.RedisConstants;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.product.service.converter.ProductStockServiceConverter;
import com.salute.mall.product.service.enums.StockTransactionOperateTypeEnum;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDaoDTO;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockSkuDTO;
import com.salute.mall.product.service.pojo.entity.ProductStock;
import com.salute.mall.product.service.pojo.entity.ProductStockTransaction;
import com.salute.mall.product.service.repository.ProductStockRepository;
import com.salute.mall.product.service.repository.ProductStockTransactionRepository;
import com.salute.mall.product.service.service.ProductStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private ProductStockTransactionRepository productStockTransactionRepository;

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
            log.error("操作库存获取锁异常,req:{}", JSON.toJSONString(dto),e);
            throw new BusinessException("500","操作库存获取锁异常");
        }catch (Exception e){
            log.error("操作库存异常,req:{}", JSON.toJSONString(dto),e);
            throw new BusinessException("500","操作库存异常");
        }finally {
            // 判断当前线程已加锁 如果把trylock放在try外面则不需要判断  redission自动通过线程ID判断是否时本线程释放锁
            if(lock.isLocked()&& lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    /**
     * @Description 扣减库存
     * @author liuhu
     * @param dto
     * @date 2022/12/6 21:53
     * @return void
     */
    private void doOperateFreezeStock(OperateFreezeStockDTO dto) {
      List<OperateFreezeStockDaoDTO> daoDTO =  buildOperateFreezeStockDaoDTO(dto);
      transactionTemplate.execute(ts->{
          int transactionRows = saveProductStockTransaction(dto);
          // 开启事务 update 如果where条件命中索引则是行级锁
          int rows =  productStockRepository.batchOperateFreezeStock(daoDTO);
          if(rows != CollectionUtils.size(dto.getSkuStockList())){
              ts.setRollbackOnly();
              log.error("库存扣减失败,req:{}",JSON.toJSONString(dto));
             throw new BusinessException("500",dto.getBizCode()+"库存扣减失败");
          }
          if(rows != transactionRows){
              log.error("新增库存流水失败,req:{}",JSON.toJSONString(dto));
              ts.setRollbackOnly();
              throw new BusinessException("500",dto.getBizCode()+"新增库存流水失败");
          }
         return null;
      });
    }

    /**
     * @Description 保存库存流水
     * @author liuhu
     * @param dto
     * @date 2022/12/6 21:53
     * @return int
     */
    private int saveProductStockTransaction(OperateFreezeStockDTO dto) {
        List<String> skuCodeList = dto.getSkuStockList().stream().map(OperateFreezeStockSkuDTO::getSkuCode).collect(Collectors.toList());
        List<ProductStock> productStocks = productStockRepository.queryBySkuCodeList(skuCodeList);
        List<ProductStockTransaction> stockTransactions = buildInsertProductStockTransaction(dto, productStocks);
        return   productStockTransactionRepository.batchInsert(stockTransactions);
    }

    /**
     * @Description 构建库存流水
     * @author liuhu
     * @param dto
     * @param productStocks
     * @date 2022/12/6 21:53
     * @return java.util.List<com.salute.mall.product.service.pojo.entity.ProductStockTransaction>
     */
    private List<ProductStockTransaction> buildInsertProductStockTransaction(OperateFreezeStockDTO dto, List<ProductStock> productStocks) {
        Map<String, ProductStock> productStockMap = productStocks.stream().collect(Collectors.toMap(ProductStock::getSkuCode, Function.identity(), (k1, k2) -> k1));
        return dto.getSkuStockList().stream().map(skuStock -> {
            ProductStock stock = productStockMap.get(skuStock.getSkuCode());
            if(Objects.isNull(stock)){
                throw new BusinessException("500",skuStock.getSkuCode()+"商品库存信息不存在");
            }
            ProductStockTransaction productStockTransaction = new ProductStockTransaction();
            productStockTransaction.setBizCode(dto.getBizCode());
            productStockTransaction.setOperateTime(new Date());
            productStockTransaction.setOperateType(StockTransactionOperateTypeEnum.FREEZING_STOCK.getValue());
            productStockTransaction.setOperateStock(skuStock.getStockNum());
            productStockTransaction.setBeforeRealStock(stock.getRealStock());
            productStockTransaction.setAfterRealStock(stock.getRealStock());
            productStockTransaction.setBeforeFreezeStock(stock.getFreezeStock());
            int afterFreezeStock = stock.getFreezeStock() + skuStock.getStockNum();
            productStockTransaction.setAfterFreezeStock(afterFreezeStock);
            productStockTransaction.setBeforeAvailableStock(stock.getAvailableStock());
            int afterAvailableStock = stock.getRealStock() - afterFreezeStock;
            productStockTransaction.setAfterAvailableStock(afterAvailableStock);
            productStockTransaction.setCreator(dto.getOperator());
            productStockTransaction.setCreatorCode(dto.getOperateCode());
            return productStockTransaction;
        }).collect(Collectors.toList());
    }

    private List<OperateFreezeStockDaoDTO> buildOperateFreezeStockDaoDTO(OperateFreezeStockDTO dto) {
      return   dto.getSkuStockList().stream().map(skuStock->{
            OperateFreezeStockDaoDTO daoDTO = new OperateFreezeStockDaoDTO();
            daoDTO.setOperateCode(dto.getOperateCode());
            daoDTO.setOperator(dto.getOperator());
            daoDTO.setBizCode(dto.getBizCode());
            daoDTO.setSkuCode(skuStock.getSkuCode());
            daoDTO.setStockNum(skuStock.getStockNum());
            return daoDTO;
        }).collect(Collectors.toList());
    }


}
