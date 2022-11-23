package com.salute.mall.product.service.service.impl;

import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.pojo.entity.ProductCategory;
import com.salute.mall.product.service.pojo.entity.ProductSku;
import com.salute.mall.product.service.pojo.entity.ProductSpu;
import com.salute.mall.product.service.repository.ProductCategoryRepository;
import com.salute.mall.product.service.repository.ProductSkuRepository;
import com.salute.mall.product.service.repository.ProductSpuRepository;
import com.salute.mall.product.service.service.ProductInfoService;
import com.salute.mall.product.service.service.ProductSpuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductSpuRepository productSpuRepository;

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Override
    public void getProductByCategoryCode(String categoryCode) {
        SaluteAssertUtil.isTrue(StringUtils.isNoneBlank(categoryCode), "分类编号不能为空");
        // TODO 转到ES  多线程
        ProductCategory productCategory = productCategoryRepository.getByCategoryCode(categoryCode);
        SaluteAssertUtil.isTrue(Objects.nonNull(productCategory), categoryCode + "分类不存在");
        SaluteAssertUtil.isTrue(Objects.equals(productCategory.getStatus(), "ENABLE"), categoryCode + "分类已禁用");
        List<ProductSpu> productSpuList  = productSpuRepository.queryByCategoryCodeThird(categoryCode);
        List<String> spuCodeList = productSpuList.stream().map(ProductSpu::getSpuCode).distinct().collect(Collectors.toList());
        List<ProductSku> productSkus = productSkuRepository.queryBySpuCodeList(spuCodeList);
        Map<String, ProductSku> skuMap = productSkus.stream().collect(Collectors.toMap(ProductSku::getSpuCode, Function.identity(), (k1, k2) -> k1));
        for (ProductSpu productSpu : productSpuList) {

        }
    }
}
