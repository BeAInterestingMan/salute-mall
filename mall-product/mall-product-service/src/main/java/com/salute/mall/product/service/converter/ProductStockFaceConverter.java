package com.salute.mall.product.service.converter;

import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStockFaceConverter {
    OperateFreezeStockDTO convertToOperateFreezeStockDTO(OperateFreezeStockRequest request);
}
