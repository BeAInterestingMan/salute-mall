package com.salute.mall.product.service.converter;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.product.api.response.ProductCustomInfoResponse;
import com.salute.mall.product.api.response.ProductDetailCustomInfoResponse;
import com.salute.mall.product.api.response.ProductSkuPloyDetailResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.*;
import com.salute.mall.product.service.pojo.request.ProductAssociatedRequest;
import com.salute.mall.product.service.pojo.request.ProductCustomerInfoRequest;
import com.salute.mall.product.service.pojo.response.ProductAssociatedResponse;
import com.salute.mall.product.service.pojo.response.ProductListInfoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductFaceConverter {

    Page<List<ProductListInfoResponse>> convertToProductListInfoResponsePage(Page<List<ProductListInfoBO>> page);

    ProductDetailCustomInfoResponse convertToProductDetailInfoResponse(ProductDetailInfoBO productDetail);

    List<ProductSkuResponse> convertToProductPloySkuInfoResponseList(List<ProductSkuDTO> ploySkuInfoDTOS);

    ProductSkuResponse convertToProductPloySkuInfoResponse(ProductSkuDTO ploySkuInfoDTO);

    ProductAssociatedResponse convertToProductAssociatedDTO(ProductAssociatedDTO associatedDTO);

    ProductCustomerInfoQueryDTO convertToProductCustomerInfoDTO(ProductCustomerInfoRequest request);

    ProductAssociatedQueryDTO convertToProductAssociatedQueryDTO(ProductAssociatedRequest request);

    ProductCustomInfoResponse convertToProductCustomInfoResponse(ProductBaseDTO productBaseInfo);

    ProductSkuPloyDetailResponse convertToProductSkuPloyDetailResponse(ProductSkuDTO ploySkuInfoDTO);

    List<ProductSkuPloyDetailResponse> convertToProductSkuPloyDetailResponseList(List<ProductSkuDTO> ploySkuInfoDTOS);
}
