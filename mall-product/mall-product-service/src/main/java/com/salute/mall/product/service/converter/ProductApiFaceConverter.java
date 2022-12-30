package com.salute.mall.product.service.converter;

import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductInfoResponse;
import com.salute.mall.product.api.response.ProductSkuBaseInfoResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductApiFaceConverter {

    QueryProductPageDTO convertToQueryProductPageDTO(QueryProductPageRequest request);

    List<ProductSkuBaseInfoResponse> convertToProductSkuBaseInfoResponse(List<ProductSkuDTO> productSkuDTOS);
}
