package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.product.service.mapper.ProductDetailMapper;
import com.salute.mall.product.service.pojo.entity.ProductDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDetailRepository {

    @Autowired
    private ProductDetailMapper productDetailMapper;

    public List<ProductDetail> queryByProductCodeList(List<String> productCodeList) {
        if(CollectionUtils.isEmpty(productCodeList)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<ProductDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductDetail::getDeleteFlag,"NO")
                    .in(ProductDetail::getProductCode,productCodeList);
        return   productDetailMapper.selectList(queryWrapper);
    }

    public ProductDetail getByProductCode(String productCode) {
        if(StringUtils.isBlank(productCode)){
            return null;
        }
        LambdaQueryWrapper<ProductDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductDetail::getDeleteFlag,"NO")
                .eq(ProductDetail::getProductCode,productCode);
        return   productDetailMapper.selectOne(queryWrapper);
    }
}
