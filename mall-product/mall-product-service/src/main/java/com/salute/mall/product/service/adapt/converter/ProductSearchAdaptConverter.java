package com.salute.mall.product.service.adapt.converter;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.product.service.adapt.dto.ProductListSearchResDTO;
import com.salute.mall.product.service.pojo.dto.ProductListInfoDTO;
import com.salute.mall.search.api.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductSearchAdaptConverter {
    QueryH5ProductPageRequest convertToQueryH5ProductPageRequest(ProductListInfoDTO infoDTO);

    Page<List<ProductListSearchResDTO>> convertToProductListSearchResDTO(Page<List<ProductListSearchResponse>> listPage);
}
