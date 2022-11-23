package com.salute.mall.product.service.repository;

import com.salute.mall.product.service.mapper.ProductStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductStockRepository {

    @Autowired
    private ProductStockMapper productStockMapper;
}
