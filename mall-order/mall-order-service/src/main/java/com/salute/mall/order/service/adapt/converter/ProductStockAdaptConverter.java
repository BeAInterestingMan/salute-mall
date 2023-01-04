package com.salute.mall.order.service.adapt.converter;

import com.salute.mall.order.service.adapt.dto.OperateFreezeStockAdaptDTO;
import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStockAdaptConverter {
    OperateFreezeStockRequest convertToOperateFreezeStockRequest(OperateFreezeStockAdaptDTO adaptDTO);
}
