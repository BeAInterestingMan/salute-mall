package com.salute.mall.product.service.adapt.converter;

import com.salute.mall.common.core.entity.Page;
import com.salute.mall.product.service.adapt.dto.ProductListSearchResDTO;
import com.salute.mall.product.service.adapt.dto.ProductSearchAssociatedDTO;
import com.salute.mall.product.service.pojo.dto.ProductAssociatedQueryDTO;
import com.salute.mall.product.service.pojo.dto.ProductCustomerInfoQueryDTO;
import com.salute.mall.search.api.pojo.request.ProductListSearchPageRequest;
import com.salute.mall.search.api.pojo.request.ProductSearchAssociatedRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import com.salute.mall.search.api.pojo.response.ProductSearchAssociatedResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductSearchAdaptConverter {

    Page<List<ProductListSearchResDTO>> convertToProductListSearchResDTO(Page<List<ProductListSearchResponse>> listPage);

    ProductSearchAssociatedDTO convertToProductSearchAssociatedDTO(ProductSearchAssociatedResponse data);


    ProductListSearchPageRequest convertToProductListSearchPageRequest(ProductCustomerInfoQueryDTO infoDTO);

    ProductSearchAssociatedRequest convertToProductSearchAssociatedRequest(ProductAssociatedQueryDTO infoDTO);
}
