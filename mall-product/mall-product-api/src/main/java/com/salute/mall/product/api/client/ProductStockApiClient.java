package com.salute.mall.product.api.client;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.follback.ProductDetailInfoClientFallback;
import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "mall-product", contextId = "saluteProductStockApiClient", fallbackFactory = ProductDetailInfoClientFallback.class)
public interface ProductStockApiClient {

    @PostMapping("/stock/api/operateFreezeStock")
    @ApiOperation("操作商品冻结库存")
    Result<Void> operateFreezeStock(@Valid @RequestBody OperateFreezeStockRequest request);
}
