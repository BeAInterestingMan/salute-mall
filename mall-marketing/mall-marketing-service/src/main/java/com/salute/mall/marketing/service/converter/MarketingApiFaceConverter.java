package com.salute.mall.marketing.service.converter;

import com.salute.mall.marketing.api.request.PrepareOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.PrepareOrderResponse;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderDTO;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderServiceDTO;
import com.salute.mall.marketing.service.pojo.dto.UseCouponServiceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketingApiFaceConverter {
    UseCouponServiceDTO convertToUseCouponServiceDTO(UseCouponRequest request);

    PrepareOrderServiceDTO convertToPrepareOrderServiceDTO(PrepareOrderRequest request);

    PrepareOrderResponse convertToPrepareOrderResponse(PrepareOrderDTO orderDTO);
}