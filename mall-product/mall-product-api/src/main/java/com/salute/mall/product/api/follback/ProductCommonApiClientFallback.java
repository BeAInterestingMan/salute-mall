package com.salute.mall.product.api.follback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.client.ProductApiClient;
import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductInfoResponse;
import com.salute.mall.product.api.response.ProductResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Component
@Slf4j
public class ProductCommonApiClientFallback implements FallbackFactory<ProductApiClient> {

    @Override
    public ProductApiClient create(Throwable throwable) {
      return new ProductApiClient() {
          @Override
          public Result<Page<List<ProductResponse>>> queryProductPage(QueryProductPageRequest request) {
              log.error("execute ProductApiClient queryProductPage error,request:{}", JSON.toJSONString(request),throwable);
              return Result.error("500","服务繁忙");
          }

          @Override
          public Result<ProductInfoResponse> getProductDetail(@NotBlank String productCode) {
              log.error("execute ProductApiClient getProductDetail error,request:{}", JSON.toJSONString(productCode),throwable);
              return Result.error("500","服务繁忙");
          }

          @Override
          public Result<List<ProductSkuResponse>> queryProductSkuList(List<String> productCodeList) {
              log.error("execute ProductApiClient getProductDetail error,request:{}", JSON.toJSONString(productCodeList),throwable);
              return Result.error("500","服务繁忙");
          }
      };
    }
}
