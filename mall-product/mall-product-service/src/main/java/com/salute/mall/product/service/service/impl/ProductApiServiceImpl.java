package com.salute.mall.product.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.product.service.converter.ProductApiServiceConverter;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import com.salute.mall.product.service.pojo.entity.ProductSku;
import com.salute.mall.product.service.repository.ProductRepository;
import com.salute.mall.product.service.repository.ProductSkuRepository;
import com.salute.mall.product.service.service.ProductApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductApiServiceImpl implements ProductApiService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Autowired
    private ProductApiServiceConverter productApiServiceConverter;

    @Override
    public IPage<Product> queryProductPage(QueryProductPageDTO queryProductPageDTO) {
       return productRepository.queryProductPage(queryProductPageDTO);
    }

    @Override
    public List<ProductSkuBaseDTO> queryProductSkuList(List<String> productSkuCodeList) {
         List<ProductSku> productSkuList = productSkuRepository.queryByProductCodeList(productSkuCodeList);
        return productApiServiceConverter.convertToProductSkuBaseDTO(productSkuList);
    }

}
