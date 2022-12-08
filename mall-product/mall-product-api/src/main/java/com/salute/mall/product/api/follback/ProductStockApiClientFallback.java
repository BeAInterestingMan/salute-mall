package com.salute.mall.product.api.follback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.client.ProductStockApiClient;
import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@Slf4j
public class ProductStockApiClientFallback implements FallbackFactory<ProductStockApiClient> {

    @Override
    public ProductStockApiClient create(Throwable throwable) {
        return new ProductStockApiClient() {
            @Override
            public Result<Void> operateFreezeStock(@Valid OperateFreezeStockRequest request) {
                log.error("execute ProductSearchClient queryProductSkuDetail error,request:{}", JSON.toJSONString(request),throwable);
                return Result.error("500","服务繁忙");
            }
        };
    }
}
