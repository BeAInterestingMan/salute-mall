package com.salute.mall.product.service.service;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductListInfoDTO;
import com.salute.mall.product.service.pojo.dto.ProductPloySkuInfoDTO;

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

    /**
     * @Description 获取商品sku详情
     * @author liuhu
     * @param skuCodeList
     * @date 2022/12/1 15:59
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductPloySkuInfoDTO>
     */
    List<ProductPloySkuInfoDTO> queryProductSkuDetail(List<String> skuCodeList);

    /**
     * @Description skuCode查询商品详情
     * @author liuhu
     * @param skuCode
     * @date 2022/12/1 16:52
     * @return com.salute.mall.product.service.pojo.dto.ProductPloySkuInfoDTO
     */
    ProductPloySkuInfoDTO getProductSkuDetail(String skuCode);
}
