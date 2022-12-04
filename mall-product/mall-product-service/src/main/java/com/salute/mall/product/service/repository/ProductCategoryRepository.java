package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.mapper.ProductCategoryMapper;
import com.salute.mall.product.service.pojo.entity.ProductCategory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCategoryRepository {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * @Description 获取全部分类
     * @author liuhu
     * @date 2022/11/23 18:36
     * @return java.util.List<com.salute.mall.product.service.pojo.entity.ProductCategory>
     */
    public List<ProductCategory> queryAllEnableCategoryList() {
        LambdaQueryWrapper<ProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductCategory::getDeleteFlag,"NO")
                    .eq(ProductCategory::getStatus,"ENABLE");
        return  productCategoryMapper.selectList(queryWrapper);
    }

    /**
     * @Description 分类编号查询
     * @author liuhu
     * @param categoryCode
     * @date 2022/11/23 18:35
     * @return com.salute.mall.product.service.pojo.entity.ProductCategory
     */
    public ProductCategory getByCategoryCode(String categoryCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNotBlank(categoryCode), "分类编号不能为空");
        LambdaQueryWrapper<ProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductCategory::getDeleteFlag,"NO")
                    .eq(ProductCategory::getCategoryCode,categoryCode);
        return  productCategoryMapper.selectOne(queryWrapper);
    }

    public List<ProductCategory> queryByCategoryCode(List<String> categoryCode) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(categoryCode), "分类编号不能为空");
        LambdaQueryWrapper<ProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductCategory::getDeleteFlag,"NO")
                .in(ProductCategory::getCategoryCode,categoryCode);
        return  productCategoryMapper.selectList(queryWrapper);
    }
}
