package com.salute.mall.search.service;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.pojo.dto.product.ProductListSearchPageDTO;
import com.salute.mall.search.pojo.dto.product.ProductSearchAssociatedDTO;
import com.salute.mall.search.pojo.entity.EsAggBaseDTO;
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
    Page<List<ProductEsEntity>> searchProduct(ProductListSearchPageDTO dto);

    /**
     * @Description 查询搜索商品的关联信息
     * @author liuhu
     * @param dto
     * @param groupKey
     * @param groupName
     * @date 2022/12/4 16:05
     * @return java.util.List<com.salute.mall.search.pojo.entity.EsAggBaseDTO>
     */
    List<EsAggBaseDTO>  searchProductAssociated(ProductSearchAssociatedDTO dto, String groupKey, String groupName);


    /**
     * @Description 保存商品Es数据
     * @author liuhu
     * @param entity
     * @date 2022/12/5 16:46
     * @return void
     */
    void upsertProduct(ProductEsEntity entity);
}
