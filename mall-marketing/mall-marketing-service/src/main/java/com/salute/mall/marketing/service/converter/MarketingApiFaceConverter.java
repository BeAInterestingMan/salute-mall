package com.salute.mall.marketing.service.converter;

import com.salute.mall.marketing.api.request.*;
import com.salute.mall.marketing.api.response.PrepareOrderResponse;
import com.salute.mall.marketing.api.response.QueryProductCouponInfoResponse;
import com.salute.mall.marketing.api.response.SubmitOrderResponse;
import com.salute.mall.marketing.service.pojo.dto.*;
import com.salute.mall.marketing.service.pojo.dto.discount.SubmitOrderResultDTO;
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

    SubmitOrderDTO convertToSubmitOrderDTO(SubmitOrderRequest request);

    SubmitOrderResponse convertToSubmitOrderResponse(SubmitOrderResultDTO orderResultDTO);

    ReturnCouponServiceDTO convertToReturnCouponServiceDTO(ReturnCouponRequest request);
}
