package com.salute.mall.product.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import com.salute.mall.product.service.repository.ProductRepository;
import com.salute.mall.product.service.service.ProductCommonApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductCommonApiServiceImpl implements ProductCommonApiService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public IPage<Product> queryProductPage(QueryProductPageDTO queryProductPageDTO) {
       return productRepository.queryProductPage(queryProductPageDTO);
    }

}
