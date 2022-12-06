package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStockServiceConverter {
    OperateFreezeStockDaoDTO convertToOperateFreezeStockDaoDTO(OperateFreezeStockDTO dto);
}
