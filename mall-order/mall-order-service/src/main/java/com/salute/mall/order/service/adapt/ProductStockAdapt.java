package com.salute.mall.order.service.adapt;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.marketing.api.client.MarketingApiClient;
import com.salute.mall.marketing.api.request.ReturnCouponRequest;
import com.salute.mall.order.service.adapt.converter.ProductStockAdaptConverter;
import com.salute.mall.order.service.adapt.dto.OperateFreezeStockAdaptDTO;
import com.salute.mall.order.service.pojo.dto.CancelOrderDTO;
import com.salute.mall.product.api.client.ProductStockApiClient;
import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class ProductStockAdapt {

    @Autowired
    private ProductStockApiClient productStockApiClient;

    @Autowired
    private ProductStockAdaptConverter productStockAdaptConverter;

    public void operateFreezeStock(OperateFreezeStockAdaptDTO adaptDTO){
        OperateFreezeStockRequest request = productStockAdaptConverter.convertToOperateFreezeStockRequest(adaptDTO);
        log.info("execute operateFreezeStock info,req:{}",JSON.toJSONString(request));
        Result<Void> result = productStockApiClient.operateFreezeStock(request);
        log.info("execute operateFreezeStock info,req:{},resp:{}",JSON.toJSONString(request),JSON.toJSONString(result));
        if(Objects.isNull(result) || !Objects.equals(result.isSuccess(),Boolean.TRUE)){
            log.error("execute operateFreezeStock error,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(result));
            throw new BusinessException("500","扣减库存异常");
        }
    }
}
