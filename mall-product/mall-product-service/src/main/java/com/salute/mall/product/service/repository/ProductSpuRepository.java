package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.mapper.ProductSpuMapper;
import com.salute.mall.product.service.pojo.entity.ProductCategory;
import com.salute.mall.product.service.pojo.entity.ProductSpu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSpuRepository {

    @Autowired
    private ProductSpuMapper productSpuMapper;

    public List<ProductSpu> queryByCategoryCodeThird(String categoryCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNoneBlank(categoryCode), "分类编号不能为空");
        SaluteAssertUtil.isTrue(StringUtils.isNoneBlank(categoryCode), "分类编号不能为空");
        LambdaQueryWrapper<ProductSpu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductSpu::getDeleteFlag,"NO")
                    .eq(ProductSpu::getStatus,"ON")
                    .eq(ProductSpu::getCategoryCodeThird,categoryCode);
        return  productSpuMapper.selectList(queryWrapper);
    }
}
