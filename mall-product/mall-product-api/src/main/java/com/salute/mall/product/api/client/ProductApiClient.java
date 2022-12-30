package com.salute.mall.product.api.client;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.follback.ProductApiClientFallback;
import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductResponse;
import com.salute.mall.product.api.response.ProductSkuBaseInfoResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "mall-product", contextId = "saluteProductApiClient", fallbackFactory = ProductApiClientFallback.class)
public interface ProductApiClient {

    @GetMapping("/product/api/queryProductPage")
    Result<Page<List<ProductResponse>>> queryProductPage(QueryProductPageRequest request);

    @GetMapping("/product/api/queryProductSkuList")
    Result<List<ProductSkuBaseInfoResponse>> queryProductSkuList(List<String> productCodeList);
}
