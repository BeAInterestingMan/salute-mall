package com.salute.mall.product.service.service;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductListInfoDTO;

import java.util.List;

public interface ProductInfoService {

    /**
     * @Description 获取小程序商品详情
     * @author liuhu
     * @param productCode
     * @date 2022/11/29 20:01
     * @return com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO
     */
    ProductDetailInfoBO getProductDetail(String productCode);

    /**
     * @Description 小程序查询分页
     * @author liuhu
     * @param infoDTO
     * @date 2022/11/29 17:43
     * @return com.salute.mall.common.core.entity.Page<java.util.List<com.salute.mall.product.service.pojo.bo.ProductListInfoBO>>
     */
    Page<List<ProductListInfoBO>> queryProductList(ProductListInfoDTO infoDTO);
}
