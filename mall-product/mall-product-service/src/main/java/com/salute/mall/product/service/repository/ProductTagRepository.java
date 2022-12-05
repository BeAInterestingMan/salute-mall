package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.mapper.ProductTagMapper;
import com.salute.mall.product.service.pojo.entity.ProductTag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTagRepository {

    @Autowired
    private ProductTagMapper productTagMapper;

    public List<ProductTag> queryByProductCodeList(List<String> productCodeList) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(productCodeList), "分类编号不能为空");
        LambdaQueryWrapper<ProductTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductTag::getDeleteFlag,"NO")
                .eq(ProductTag::getStatus,"ON")
                .in(ProductTag::getProductCode,productCodeList);
        return  productTagMapper.selectList(queryWrapper);
    }

    public List<ProductTag> getByProductCode(String productCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(productCode), "分类编号不能为空");
        LambdaQueryWrapper<ProductTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductTag::getDeleteFlag,"NO")
                .eq(ProductTag::getStatus,"ON")
                .in(ProductTag::getProductCode,productCode);
        return  productTagMapper.selectList(queryWrapper);
    }
}
