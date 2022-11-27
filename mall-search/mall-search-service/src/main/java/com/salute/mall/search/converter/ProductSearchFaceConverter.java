package com.salute.mall.search.converter;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import com.salute.mall.search.pojo.reponse.ProductListPageResponse;
import com.salute.mall.search.pojo.request.QueryH5ProductPageRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductSearchFaceConverter {

    QueryH5ProductPageDTO convertToQueryH5ProductPageDTO(QueryH5ProductPageRequest request);

    Page<ProductListPageResponse> convertToProductListPageResponsePage(Page<ProductEsEntity> esEntityPage);
}
