package com.salute.mall.product.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.converter.ProductApiServiceConverter;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuSpecificationDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import com.salute.mall.product.service.pojo.entity.ProductSku;
import com.salute.mall.product.service.pojo.entity.ProductStock;
import com.salute.mall.product.service.repository.ProductRepository;
import com.salute.mall.product.service.repository.ProductSkuRepository;
import com.salute.mall.product.service.repository.ProductStockRepository;
import com.salute.mall.product.service.service.ProductApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductApiServiceImpl implements ProductApiService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Autowired
    private ProductApiServiceConverter productApiServiceConverter;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Override
    public IPage<Product> queryProductPage(QueryProductPageDTO queryProductPageDTO) {
       return productRepository.queryProductPage(queryProductPageDTO);
    }

    @Override
    public List<ProductSkuDTO> queryProductSkuList(List<String> productSkuCodeList) {
        List<ProductSku> productSkuList = productSkuRepository.queryByProductCodeList(productSkuCodeList);
        List<String> skuCodeList = productSkuList.stream().map(ProductSku::getSkuCode).collect(Collectors.toList());
        List<ProductStock> productStockList = productStockRepository.queryBySkuCodeList(skuCodeList);
        return buildSkuInfoDTOList(productSkuList, productStockList);
    }

    /**
     * @Description 构建商品sku信息
     * @author liuhu
     * @param productSkus
     * @param productStockList
     * @date 2022/11/29 20:16
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductSkuDTO>
     */
    private List<ProductSkuDTO> buildSkuInfoDTOList(List<ProductSku> productSkus,
                                                    List<ProductStock> productStockList) {
        Map<String, ProductStock> stockMap = productStockList.stream().collect(Collectors.toMap(ProductStock::getSkuCode, Function.identity(), (k1, k2) -> k1));
        return  productSkus.stream().map(sku->{
            //1.基本sku信息
            ProductSkuDTO dto = productApiServiceConverter.convertToProductSkuDTO(sku);
            ProductStock productStock = stockMap.get(sku.getSkuCode());
            SaluteAssertUtil.isTrue(Objects.nonNull(productStock),sku.getSkuCode()+"sku库存信息不存在");
            //2.可用库存
            dto.setAvailableStock(productStock.getAvailableStock());
            SaluteAssertUtil.isTrue(StringUtils.isNotBlank(sku.getSpecificationJson()),sku.getSkuCode()+"sku规格信息不存在");
            //3.sku的规格信息 json存储
            List<ProductSkuSpecificationDTO> specificationDTOList = JSON.parseArray(sku.getSpecificationJson(), ProductSkuSpecificationDTO.class);
            dto.setSkuSpecificationList(specificationDTOList);
            return dto;
        }).collect(Collectors.toList());
    }

}
