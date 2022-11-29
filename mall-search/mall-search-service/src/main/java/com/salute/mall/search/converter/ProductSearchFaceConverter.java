package com.salute.mall.search.converter;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.api.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductSearchFaceConverter {

    QueryH5ProductPageDTO convertToQueryH5ProductPageDTO(QueryH5ProductPageRequest request);

    Page<List<ProductListSearchResponse>> convertToProductListPageResponsePage(Page<List<ProductEsEntity>> esEntityPage);
}
