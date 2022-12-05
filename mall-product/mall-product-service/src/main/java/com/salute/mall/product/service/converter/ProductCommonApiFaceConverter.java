package com.salute.mall.product.service.converter;

import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductInfoResponse;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCommonApiFaceConverter {

    QueryProductPageDTO convertToQueryProductPageDTO(QueryProductPageRequest request);

    ProductInfoResponse convertToProductInfoResponse(ProductDetailInfoBO productDetail);
}
