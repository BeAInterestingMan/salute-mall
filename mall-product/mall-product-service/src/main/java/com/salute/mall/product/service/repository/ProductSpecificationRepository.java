package com.salute.mall.product.service.repository;

import com.salute.mall.product.service.mapper.ProductSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationRepository {

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
}
