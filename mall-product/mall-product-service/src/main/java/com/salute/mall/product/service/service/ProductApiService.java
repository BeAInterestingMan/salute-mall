package com.salute.mall.product.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductBaseDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;

import java.util.List;

public interface ProductApiService {
    IPage<Product> queryProductPage(QueryProductPageDTO queryProductPageDTO);

    /**
     * @Description 获取商品sku信息（包含可用库存）
     * @author liuhu
     * @param productSkuCodeList
     * @date 2022/12/30 16:52
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductSkuDTO>
     */
    List<ProductSkuDTO> queryProductSkuList(List<String> productSkuCodeList);
}
