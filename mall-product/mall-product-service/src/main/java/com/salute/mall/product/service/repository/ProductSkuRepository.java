package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.product.service.mapper.ProductSkuMapper;
import com.salute.mall.product.service.pojo.entity.ProductSku;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
     * @param productCodeList
     * @date 2022/11/23 19:12
     * @return java.util.List<com.salute.mall.product.service.pojo.entity.ProductSku>
     */
    public List<ProductSku> queryByProductCodeList(List<String> productCodeList) {
        if(CollectionUtils.isEmpty(productCodeList)){
             return Lists.newArrayList();
        }
        LambdaQueryWrapper<ProductSku> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductSku::getDeleteFlag,"NO")
                .eq(ProductSku::getStatus,"ON")
                .in(ProductSku::getProductCode,productCodeList);
      return   productSkuMapper.selectList(queryWrapper);
    }

    public List<ProductSku> queryByProductCode(String productCode) {
        if(StringUtils.isBlank(productCode)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<ProductSku> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductSku::getDeleteFlag,"NO")
                .eq(ProductSku::getStatus,"ON")
                .eq(ProductSku::getProductCode,productCode);
        return   productSkuMapper.selectList(queryWrapper);
    }
}
