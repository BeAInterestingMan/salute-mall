package com.salute.mall.product.api.follback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.client.ProductDetailInfoClient;
import com.salute.mall.product.api.response.ProductDetailInfoResponse;
import com.salute.mall.product.api.response.ProductPloySkuInfoResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Component
@Slf4j
public class ProductDetailInfoClientFallback implements FallbackFactory<ProductDetailInfoClient> {

    @Override
    public ProductDetailInfoClient create(Throwable throwable) {
        return new ProductDetailInfoClient() {
            @Override
            public Result<ProductDetailInfoResponse> getProductDetail(@NotBlank String productCode) {
                log.error("execute ProductSearchClient getProductDetail error,request:{}", JSON.toJSONString(productCode),throwable);
                return Result.error("500","服务繁忙");
            }

            @Override
            public Result<List<ProductPloySkuInfoResponse>> queryProductSkuDetail(@NotEmpty List<String> skuCodeList) {
                log.error("execute ProductSearchClient queryProductSkuDetail error,request:{}", JSON.toJSONString(skuCodeList),throwable);
                return Result.error("500","服务繁忙");
            }

            @Override
            public Result<ProductPloySkuInfoResponse> getProductSkuDetail(@NotBlank String skuCode) {
                log.error("execute ProductSearchClient getProductSkuDetail error,request:{}", JSON.toJSONString(skuCode),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
