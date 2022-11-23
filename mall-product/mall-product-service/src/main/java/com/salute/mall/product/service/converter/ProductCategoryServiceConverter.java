package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;
import com.salute.mall.product.service.pojo.entity.ProductCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryServiceConverter {
    ProductCategoryTreeDTO convertToProductCategoryTreeDTO(ProductCategory parentCategory);
}
