package com.salute.mall.product.api.client;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.follback.ProductDetailInfoClientFallback;
import com.salute.mall.product.api.response.ProductDetailCustomInfoResponse;
import com.salute.mall.product.api.response.ProductSkuPloyDetailResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@FeignClient(value = "mall-product", contextId = "saluteProductClient", fallbackFactory = ProductDetailInfoClientFallback.class)
public interface ProductClient {

    @PostMapping("/product/customer/queryProductSkuDetail")
    @ApiOperation("根据skuCodeList获取sku商品详情信息")
    Result<List<ProductSkuPloyDetailResponse>> queryProductSkuDetail(@NotEmpty @RequestBody List<String> skuCodeList);

    @GetMapping("/product/customer/getProductSkuDetail")
    @ApiOperation("根据skuCode获取sku商品详情信息")
    Result<ProductSkuPloyDetailResponse> getProductSkuDetail(@NotEmpty @RequestParam(name = "skuCode") String skuCode);
}
