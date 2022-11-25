package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.product.service.mapper.ProductSkuMapper;
import com.salute.mall.product.service.pojo.entity.ProductSku;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSkuRepository {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    /**
     * @Description spuCode查询相关sku
     * @author liuhu
     * @param spuCodeList
     * @date 2022/11/23 19:12
     * @return java.util.List<com.salute.mall.product.service.pojo.entity.ProductSku>
     */
    public List<ProductSku> queryBySpuCodeList(List<String> spuCodeList) {
        if(CollectionUtils.isEmpty(spuCodeList)){
             return Lists.newArrayList();
        }
        LambdaQueryWrapper<ProductSku> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductSku::getDeleteFlag,"NO")
                .eq(ProductSku::getStatus,"ON")
                .in(ProductSku::getSkuCode,spuCodeList);
      return   productSkuMapper.selectList(queryWrapper);
    }
}
