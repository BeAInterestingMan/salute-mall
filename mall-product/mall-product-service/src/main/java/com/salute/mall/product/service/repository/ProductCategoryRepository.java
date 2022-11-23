package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.product.service.mapper.ProductCategoryMapper;
import com.salute.mall.product.service.pojo.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCategoryRepository {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public List<ProductCategory> queryAllEnableCategoryList() {
        LambdaQueryWrapper<ProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductCategory::getDeleteFlag,"NO")
                    .eq(ProductCategory::getStatus,"ENABLE");
        return  productCategoryMapper.selectList(queryWrapper);
    }
}
