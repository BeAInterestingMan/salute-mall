package com.salute.mall.search.converter;

import com.salute.mall.search.pojo.dto.product.ProductListSearchPageDTO;
import com.salute.mall.search.pojo.dto.product.ProductSearchAssociatedDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductSearchServiceConverter {
    ProductListSearchPageDTO convertToProductListSearchPageDTO(ProductSearchAssociatedDTO dto);
}
