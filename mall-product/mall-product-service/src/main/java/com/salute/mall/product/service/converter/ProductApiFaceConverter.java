package com.salute.mall.product.service.converter;

import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductInfoResponse;
import com.salute.mall.product.api.response.ProductResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductBaseDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductApiFaceConverter {

    QueryProductPageDTO convertToQueryProductPageDTO(QueryProductPageRequest request);

    ProductInfoResponse convertToProductInfoResponse(ProductDetailInfoBO productDetail);

    List<ProductSkuResponse> convertToProductSkuResponse(List<ProductSkuBaseDTO> productSkuBaseDTOList);
}
