package com.salute.mall.marketing.service.converter;

import com.salute.mall.marketing.api.request.PrepareOrderRequest;
import com.salute.mall.marketing.api.request.QueryProductCouponInfoRequest;
import com.salute.mall.marketing.api.request.ReceiveCouponRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.PrepareOrderResponse;
import com.salute.mall.marketing.api.response.QueryProductCouponInfoResponse;
import com.salute.mall.marketing.service.pojo.dto.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarketingApiFaceConverter {
    UseCouponServiceDTO convertToUseCouponServiceDTO(UseCouponRequest request);

    PrepareOrderServiceDTO convertToPrepareOrderServiceDTO(PrepareOrderRequest request);

    PrepareOrderResponse convertToPrepareOrderResponse(PrepareOrderDTO orderDTO);

    ProductCouponInfoServiceDTO convertToProductCouponInfoServiceDTO(QueryProductCouponInfoRequest request);

    List<QueryProductCouponInfoResponse> convertToQueryProductCouponInfoResponse(List<ProductCouponInfoDTO> productCouponInfoDTOS);

    ReceiveCouponDTO convertToReceiveCouponDTO(ReceiveCouponRequest request);
}
