package com.salute.mall.product.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductBaseDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;

import java.util.List;

public interface ProductApiService {
    IPage<Product> queryProductPage(QueryProductPageDTO queryProductPageDTO);

    List<ProductSkuBaseDTO> queryProductSkuList(List<String> productSkuCodeList);
}
