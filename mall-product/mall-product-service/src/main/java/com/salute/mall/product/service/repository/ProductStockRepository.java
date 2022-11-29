package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.product.service.mapper.ProductStockMapper;
import com.salute.mall.product.service.pojo.entity.ProductStock;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductStockRepository {

    @Autowired
    private ProductStockMapper productStockMapper;

    public List<ProductStock> queryBySkuCodeList(List<String> skuCodeList) {
        if(CollectionUtils.isEmpty(skuCodeList)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<ProductStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductStock::getDeleteFlag,"NO")
                    .in(ProductStock::getSkuCode,skuCodeList);
        return  productStockMapper.selectList(queryWrapper);
    }
}
