package com.salute.mall.product.api.client;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.follback.ProductDetailInfoClientFallback;
import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductInfoResponse;
import com.salute.mall.product.api.response.ProductResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

@FeignClient(value = "mall-product", contextId = "saluteProductCommonApiClient", fallbackFactory = ProductDetailInfoClientFallback.class)
public interface ProductCommonApiClient {

    @GetMapping("/product/common/api/queryProductPage")
    Result<Page<List<ProductResponse>>> queryProductPage(QueryProductPageRequest request);

    @GetMapping("/product/common/api/getProductDetail")
    @ApiOperation("获取小程序的商品详情信息")
    Result<ProductInfoResponse> getProductDetail(@NotBlank @RequestParam(name = "productCode") String productCode);
}
