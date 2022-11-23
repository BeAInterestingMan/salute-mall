package com.salute.mall.product.service.repository;

import com.salute.mall.product.service.mapper.ProductSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSkuRepository {

    @Autowired
    private ProductSkuMapper productSkuMapper;
}
