package com.salute.mall.search.api.follback;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.search.api.client.ProductSearchClient;
import com.salute.mall.search.api.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductSearchClientFallback implements FallbackFactory<ProductSearchClient> {
    @Override
    public ProductSearchClient create(Throwable throwable) {
      return new ProductSearchClient() {
          @Override
          public Result<Page<List<ProductListSearchResponse>>> searchProduct(QueryH5ProductPageRequest request) {
              log.error("execute ProductSearchClient searchProduct error,request:{}", JSON.toJSONString(request),throwable);
              return Result.error("500","服务繁忙");
          }
      };
    }
}
