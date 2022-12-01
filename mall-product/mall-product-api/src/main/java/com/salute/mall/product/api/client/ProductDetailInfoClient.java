package com.salute.mall.product.api.client;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.follback.ProductDetailInfoClientFallback;
import com.salute.mall.product.api.response.ProductDetailInfoResponse;
import com.salute.mall.product.api.response.ProductPloySkuInfoResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@FeignClient(value = "mall-product", contextId = "saluteProductDetailClient", fallbackFactory = ProductDetailInfoClientFallback.class)
public interface ProductDetailInfoClient {

    @GetMapping("/h5/product/getProductDetail")
    @ApiOperation("获取小程序的商品详情信息")
    Result<ProductDetailInfoResponse> getProductDetail(@NotBlank @RequestParam(name = "productCode") String productCode);

    @PostMapping("/h5/product/queryProductSkuDetail")
    @ApiOperation("根据skuCodeList获取sku商品详情信息")
    Result<List<ProductPloySkuInfoResponse>> queryProductSkuDetail(@NotEmpty @RequestBody List<String> skuCodeList);

    @GetMapping("/h5/product/getProductSkuDetail")
    @ApiOperation("根据skuCode获取sku商品详情信息")
    Result<ProductPloySkuInfoResponse> getProductSkuDetail(@NotEmpty @RequestParam(name = "skuCode") String skuCode);
}
