package com.salute.mall.product.service.repository;

import com.salute.mall.product.service.mapper.ProductSpuDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSpuDetailRepository {

    @Autowired
    private ProductSpuDetailMapper productSpuDetailMapper;
}
