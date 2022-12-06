package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.mapper.ProductMapper;
import com.salute.mall.product.service.pojo.dto.ProductBaseDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> queryByProductCodeList(List<String> productCodeList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productCodeList), "分类编号不能为空");
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getDeleteFlag,"NO")
                .eq(Product::getStatus,"ON")
                .in(Product::getProductCode,productCodeList);
        return  productMapper.selectList(queryWrapper);
    }

    public Product getByProductCode(String productCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(productCode), "分类编号不能为空");
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getDeleteFlag,"NO")
                .eq(Product::getStatus,"ON")
                .eq(Product::getProductCode,productCode);
        return  productMapper.selectOne(queryWrapper);
    }

    public IPage<Product> queryProductPage(QueryProductPageDTO queryProductPageDTO) {
         IPage<Product> page = new Page<>(queryProductPageDTO.getPageIndex(),queryProductPageDTO.getPageSize());
         return productMapper.selectPage(page, new QueryWrapper<>());
    }
}
