package com.salute.mall.mq.consumer.converter;

import com.salute.mall.product.api.response.ProductBaseInfoResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.product.api.response.ProductTagResponse;
import com.salute.mall.search.api.pojo.request.ProductSaveEsRequest;
import com.salute.mall.search.api.pojo.request.ProductSkuSaveEsRequest;
import com.salute.mall.search.api.pojo.request.ProductTagSaveEsRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDetailSyncFaceConverter {

    List<ProductTagSaveEsRequest> convertToProductTagSaveEsRequestList(List<ProductTagResponse> productTagBaseList);

    List<ProductSkuSaveEsRequest> convertToProductSkuSaveEsRequestList(List<ProductSkuResponse> productPloySkuInfoList);

    ProductSaveEsRequest convertToProductSaveEsRequest(ProductBaseInfoResponse productBaseInfo);
}
