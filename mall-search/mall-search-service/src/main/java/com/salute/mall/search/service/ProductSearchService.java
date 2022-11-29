package com.salute.mall.search.service;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;

import java.util.List;

public interface ProductSearchService {
    /**
     * @Description 查询小程序商品列表
     * @author liuhu
     * @param dto
     * @date 2022/11/27 22:32
     * @return com.salute.mall.common.core.entity.Page<com.salute.mall.search.pojo.entity.ProductEsEntity>
     */
    Page<List<ProductEsEntity>> searchProduct(QueryH5ProductPageDTO dto);
}
