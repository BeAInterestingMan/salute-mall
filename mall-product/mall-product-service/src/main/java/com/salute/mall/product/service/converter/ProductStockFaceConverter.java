package com.salute.mall.product.service.converter;

import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import com.salute.mall.product.api.request.OperateRealStockRequest;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;
import com.salute.mall.product.service.pojo.dto.stock.OperateRealStockDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStockFaceConverter {
    OperateFreezeStockDTO convertToOperateFreezeStockDTO(OperateFreezeStockRequest request);

    OperateRealStockDTO convertToOperateRealStockDTO(OperateRealStockRequest request);
}
