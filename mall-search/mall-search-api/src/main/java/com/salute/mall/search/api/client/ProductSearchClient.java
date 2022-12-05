package com.salute.mall.search.api.client;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.search.api.follback.ProductSearchClientFallback;
import com.salute.mall.search.api.pojo.request.ProductListSearchPageRequest;
import com.salute.mall.search.api.pojo.request.ProductSaveEsRequest;
import com.salute.mall.search.api.pojo.request.ProductSearchAssociatedRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import com.salute.mall.search.api.pojo.response.ProductSearchAssociatedResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "mall-search", contextId = "saluteSearchClient", fallbackFactory = ProductSearchClientFallback.class)
public interface ProductSearchClient {

    @PostMapping("/search/product/detailList")
    Result<Page<List<ProductListSearchResponse>>> searchProduct(@RequestBody ProductListSearchPageRequest request);

    @PostMapping("/search/product/associated")
    Result<ProductSearchAssociatedResponse> searchProductAssociated(@Valid @RequestBody ProductSearchAssociatedRequest request);

    @PostMapping("/search/product/saveEsProduct")
    Result<Void> saveEsProduct(@Valid @RequestBody ProductSaveEsRequest request);
}
