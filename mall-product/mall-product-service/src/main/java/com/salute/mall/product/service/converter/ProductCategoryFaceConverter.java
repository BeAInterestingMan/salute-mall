package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;
import com.salute.mall.product.service.pojo.response.ProductCategoryResp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryFaceConverter {
    List<ProductCategoryResp> convertToProductCategoryRespList(List<ProductCategoryTreeDTO> productCategoryTreeDTOS);
}
