package com.salute.mall.product.service.service;

import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;

import java.util.List;

public interface ProductCategoryService {

    /**
     * @Description 获取全部分类
     * @author liuhu
     * @date 2022/11/23 13:42
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductCategoryDTO>
     */
    List<ProductCategoryTreeDTO> queryAllCategoryInfo(String categoryCode);
}
