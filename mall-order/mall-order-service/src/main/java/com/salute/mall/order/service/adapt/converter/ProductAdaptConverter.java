package com.salute.mall.order.service.adapt.converter;

import com.salute.mall.order.service.adapt.dto.ProductSkuBaseInfoDTO;
import com.salute.mall.product.api.response.ProductSkuBaseInfoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductAdaptConverter {
    List<ProductSkuBaseInfoDTO> convertToProductSkuBaseInfoDTOList(List<ProductSkuBaseInfoResponse> result);
}
