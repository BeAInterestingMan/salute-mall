package com.salute.mall.product.service.repository;

import com.salute.mall.product.service.mapper.ProductSpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSpuRepository {

    @Autowired
    private ProductSpuMapper productSpuMapper;
}
