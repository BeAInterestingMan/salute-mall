package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.ProductBaseInfoDTO;
import com.salute.mall.product.service.pojo.dto.ProductDetailInfoDTO;
import com.salute.mall.product.service.pojo.dto.ProductListInfoDTO;
import com.salute.mall.product.service.pojo.dto.ProductPloySkuInfoDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import com.salute.mall.product.service.pojo.entity.ProductDetail;
import com.salute.mall.product.service.pojo.entity.ProductSku;
import com.salute.mall.search.api.pojo.request.QueryH5ProductPageRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductInfoServiceConverter {
    QueryH5ProductPageRequest convertToQueryH5ProductPageRequest(ProductListInfoDTO infoDTO);

    ProductBaseInfoDTO convertToProductBaseInfoDTO(Product product);

    ProductDetailInfoDTO convertToProductDetailInfoDTO(ProductDetail productDetail);

    ProductPloySkuInfoDTO convertToProductPloySkuInfoDTO(ProductSku sku);
}
