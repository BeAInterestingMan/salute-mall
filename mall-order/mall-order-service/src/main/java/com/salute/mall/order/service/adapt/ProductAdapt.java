package com.salute.mall.order.service.adapt;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.marketing.api.client.MarketingApiClient;
import com.salute.mall.marketing.api.request.ReturnCouponRequest;
import com.salute.mall.marketing.api.request.SubmitOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.SubmitOrderResponse;
import com.salute.mall.order.service.adapt.converter.MarketingAdaptConverter;
import com.salute.mall.order.service.adapt.converter.ProductAdaptConverter;
import com.salute.mall.order.service.adapt.dto.ProductSkuBaseInfoDTO;
import com.salute.mall.order.service.adapt.dto.SubmitOrderAdaptDTO;
import com.salute.mall.order.service.adapt.dto.SubmitOrderDTO;
import com.salute.mall.order.service.adapt.dto.UseCouponAdaptDTO;
import com.salute.mall.order.service.pojo.dto.CancelOrderDTO;
import com.salute.mall.product.api.client.ProductApiClient;
import com.salute.mall.product.api.response.ProductSkuBaseInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ProductAdapt {

    @Autowired
    private ProductApiClient productApiClient;

    @Autowired
    private ProductAdaptConverter productAdaptConverter;

    /**
     * @Description 查询商品信息
     * @author liuhu
     * @param skuCodeList
     * @date 2023/1/4 10:34
     * @return java.util.List<com.salute.mall.order.service.adapt.dto.ProductSkuBaseInfoDTO>
     */
    public List<ProductSkuBaseInfoDTO> queryProductSkuList(List<String> skuCodeList) {
        log.info("execute useCoupon info,req:{}", JSON.toJSONString(skuCodeList));
        Result<List<ProductSkuBaseInfoResponse>> result = productApiClient.queryProductSkuList(skuCodeList);
        log.error("execute queryProductSkuList info,req:{}，resp：{}",JSON.toJSONString(skuCodeList),JSON.toJSONString(result));
        if(Objects.isNull(result) || !Objects.equals(result.isSuccess(),Boolean.TRUE)){
            throw new BusinessException("500","获取商品信息异常");
        }
        return productAdaptConverter.convertToProductSkuBaseInfoDTOList(result.getResult());
    }
}
