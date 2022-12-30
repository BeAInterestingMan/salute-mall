package com.salute.mall.product.service.repository;

import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.datasource.helper.MybatisBatchHelper;
import com.salute.mall.product.service.mapper.ProductStockTransactionMapper;
import com.salute.mall.product.service.pojo.entity.ProductStockTransaction;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductStockTransactionRepository {

    @Autowired
    private ProductStockTransactionMapper stockTransactionMapper;

    @Autowired
    private MybatisBatchHelper batchHelper;

    /**
     * @Description 批量新增库存流水
     * @author liuhu
     * @param stockTransactions
     * @date 2022/12/7 20:05
     * @return int
     */
    public int batchInsert(List<ProductStockTransaction> stockTransactions) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(stockTransactions),"批量新增库存流水异常");
        return batchHelper.batchInsertOrUpdate(stockTransactions, ProductStockTransactionMapper.class, (record,mapper)->mapper.insert(record));
    }
}
