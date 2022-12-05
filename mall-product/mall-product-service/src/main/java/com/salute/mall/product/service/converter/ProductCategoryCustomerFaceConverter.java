package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;
import com.salute.mall.product.service.pojo.response.ProductCategoryCustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryCustomerFaceConverter {
    List<ProductCategoryCustomerResponse> convertToProductCategoryRespList(List<ProductCategoryTreeDTO> productCategoryTreeDTOS);
}
