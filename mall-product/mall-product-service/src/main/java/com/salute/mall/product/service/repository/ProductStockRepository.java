package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.common.datasource.helper.MybatisBatchHelper;
import com.salute.mall.product.service.mapper.ProductStockMapper;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDaoDTO;
import com.salute.mall.product.service.pojo.entity.ProductStock;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductStockRepository {

    @Autowired
    private ProductStockMapper productStockMapper;

    @Autowired
    private MybatisBatchHelper batchHelper;

    public List<ProductStock> queryBySkuCodeList(List<String> skuCodeList) {
        if(CollectionUtils.isEmpty(skuCodeList)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<ProductStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductStock::getDeleteFlag,"NO")
                    .in(ProductStock::getSkuCode,skuCodeList);
        return  productStockMapper.selectList(queryWrapper);
    }

    public ProductStock getBySkuCode(String skuCode) {
        if(StringUtils.isBlank(skuCode)){
            return null;
        }
        LambdaQueryWrapper<ProductStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductStock::getDeleteFlag,"NO")
                .eq(ProductStock::getSkuCode,skuCode);
        return  productStockMapper.selectOne(queryWrapper);
    }

   /**
    * @Description 批量冻结库存
    * @author liuhu
    * @param dtoList
    * @date 2022/12/7 20:13
    * @return int
    */
    public int batchOperateFreezeStock(List<OperateFreezeStockDaoDTO> dtoList) {
       return batchHelper.batchInsertOrUpdate(dtoList, ProductStockMapper.class, ProductStockMapper::doOperateFreezeStock);
    }


    public int doOperateFreezeStock(List<OperateFreezeStockDaoDTO> dtoList) {
       return productStockMapper.batchOperateFreezeStock(dtoList);
    }
}
