package com.salute.mall.search.api.client;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.search.api.follback.ProductSearchClientFallback;
import com.salute.mall.search.api.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "mall-search", contextId = "saluteSearchClient", fallbackFactory = ProductSearchClientFallback.class)
public interface ProductSearchClient {

    @PostMapping("/search/product/detailList")
    Result<Page<List<ProductListSearchResponse>>> searchProduct(@RequestBody QueryH5ProductPageRequest request);
}
