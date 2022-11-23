package com.salute.mall.product.service.repository;

import com.salute.mall.product.service.mapper.ProductSpecificationDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationDetailRepository {

    @Autowired
    private ProductSpecificationDetailMapper productSpecificationDetailMapper;
}
