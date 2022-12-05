package com.salute.mall.search.converter;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.api.pojo.request.ProductListSearchPageRequest;
import com.salute.mall.search.api.pojo.request.ProductSearchAssociatedRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import com.salute.mall.search.pojo.dto.product.ProductListSearchPageDTO;
import com.salute.mall.search.pojo.dto.product.ProductSearchAssociatedDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductSearchFaceConverter {
    
    Page<List<ProductListSearchResponse>> convertToProductListPageResponsePage(Page<List<ProductEsEntity>> esEntityPage);
    
    ProductListSearchPageDTO convertToProductListSearchPageRequest(ProductListSearchPageRequest request);

    ProductListSearchPageDTO convertToProductListSearchPageDTO(ProductSearchAssociatedRequest request);

    ProductSearchAssociatedDTO convertToProductSearchAssociatedDTO(ProductSearchAssociatedRequest request);
}
