package com.salute.mall.product.service.converter;

import com.salute.mall.product.api.response.ProductBaseResponse;
import com.salute.mall.product.api.response.ProductBaseTagResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.search.api.pojo.request.ProductSaveEsRequest;
import com.salute.mall.search.api.pojo.request.ProductSkuSaveEsRequest;
import com.salute.mall.search.api.pojo.request.ProductTagSaveEsRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDetailSyncFaceConverter {

    ProductSaveEsRequest convertToProductSaveEsRequest(ProductBaseResponse productBaseResponse);

    List<ProductTagSaveEsRequest> convertToProductTagSaveEsRequestList(List<ProductBaseTagResponse> productTagBaseList);

    List<ProductSkuSaveEsRequest> convertToProductSkuSaveEsRequestList(List<ProductSkuResponse> productPloySkuInfoList);
}
