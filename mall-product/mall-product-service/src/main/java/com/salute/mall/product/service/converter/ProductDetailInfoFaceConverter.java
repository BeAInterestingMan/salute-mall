package com.salute.mall.product.service.converter;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.product.api.response.ProductDetailInfoResponse;
import com.salute.mall.product.api.response.ProductPloySkuInfoResponse;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductAssociatedDTO;
import com.salute.mall.product.service.pojo.dto.ProductAssociatedQueryDTO;
import com.salute.mall.product.service.pojo.dto.ProductCustomerInfoQueryDTO;
import com.salute.mall.product.service.pojo.dto.ProductPloySkuInfoDTO;
import com.salute.mall.product.service.pojo.request.ProductAssociatedRequest;
import com.salute.mall.product.service.pojo.request.ProductCustomerInfoRequest;
import com.salute.mall.product.service.pojo.response.ProductAssociatedResponse;
import com.salute.mall.product.service.pojo.response.ProductListInfoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDetailInfoFaceConverter {

    Page<List<ProductListInfoResponse>> convertToProductListInfoResponsePage(Page<List<ProductListInfoBO>> page);

    ProductDetailInfoResponse convertToProductDetailInfoResponse(ProductDetailInfoBO productDetail);

    List<ProductPloySkuInfoResponse> convertToProductPloySkuInfoResponseList(List<ProductPloySkuInfoDTO> ploySkuInfoDTOS);

    ProductPloySkuInfoResponse convertToProductPloySkuInfoResponse(ProductPloySkuInfoDTO ploySkuInfoDTO);

    ProductAssociatedResponse convertToProductAssociatedDTO(ProductAssociatedDTO associatedDTO);

    ProductCustomerInfoQueryDTO convertToProductCustomerInfoDTO(ProductCustomerInfoRequest request);

    ProductAssociatedQueryDTO convertToProductAssociatedQueryDTO(ProductAssociatedRequest request);
}
