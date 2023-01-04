package com.salute.mall.order.service.adapt.converter;

import com.salute.mall.marketing.api.request.SubmitOrderRequest;
import com.salute.mall.marketing.api.request.UseCouponRequest;
import com.salute.mall.marketing.api.response.SubmitOrderResponse;
import com.salute.mall.order.service.adapt.dto.SubmitOrderAdaptDTO;
import com.salute.mall.order.service.adapt.dto.SubmitOrderDTO;
import com.salute.mall.order.service.adapt.dto.UseCouponAdaptDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketingAdaptConverter {
    UseCouponRequest convertToUseCouponRequest(UseCouponAdaptDTO useCouponAdaptDTO);

    SubmitOrderRequest convertToSubmitOrderRequest(SubmitOrderAdaptDTO submitOrderAdaptDTO);

    SubmitOrderDTO convertToSubmitOrderDTO(SubmitOrderResponse result);
}
