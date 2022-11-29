package com.salute.mall.product.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.product.service.mapper.ProductSpecificationMapper;
import com.salute.mall.product.service.pojo.entity.ProductSpecification;
import com.salute.mall.product.service.pojo.entity.ProductStock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSpecificationRepository {

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;

    /**
     * @Description 获取规格项
     * @author liuhu
     * @param productCode
     * @date 2022/11/29 20:22
     * @return java.util.List<com.salute.mall.product.service.pojo.entity.ProductSpecification>
     */
    public List<ProductSpecification> queryByProductCode(String productCode) {
        if(StringUtils.isBlank(productCode)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<ProductSpecification> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductSpecification::getDeleteFlag,"NO")
                    .eq(ProductSpecification::getProductCode,productCode);
        return productSpecificationMapper.selectList(queryWrapper);
    }
}
