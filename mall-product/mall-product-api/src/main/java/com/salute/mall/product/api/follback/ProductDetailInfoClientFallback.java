package com.salute.mall.product.api.follback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.client.ProductClient;
import com.salute.mall.product.api.response.ProductDetailCustomInfoResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Component
@Slf4j
public class ProductDetailInfoClientFallback implements FallbackFactory<ProductClient> {

    @Override
    public ProductClient create(Throwable throwable) {
        return new ProductClient() {
            @Override
            public Result<List<ProductSkuResponse>> queryProductSkuDetail(@NotEmpty List<String> skuCodeList) {
                log.error("execute ProductSearchClient queryProductSkuDetail error,request:{}", JSON.toJSONString(skuCodeList),throwable);
                return Result.error("500","服务繁忙");
            }

            @Override
            public Result<ProductSkuResponse> getProductSkuDetail(@NotBlank String skuCode) {
                log.error("execute ProductSearchClient getProductSkuDetail error,request:{}", JSON.toJSONString(skuCode),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
