package com.salute.mall.product.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;

public interface ProductCommonApiService {
    IPage<Product> queryProductPage(QueryProductPageDTO queryProductPageDTO);
}
