package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.*;
import com.salute.mall.product.service.pojo.entity.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductInfoServiceConverter {

    ProductBaseInfoDTO convertToProductBaseInfoDTO(Product product);

    ProductDetailInfoDTO convertToProductDetailInfoDTO(ProductDetail productDetail);

    ProductPloySkuInfoDTO convertToProductPloySkuInfoDTO(ProductSku sku);

    List<ProductTagBaseDTO> convertToProductTagBaseDTOList(List<ProductTag> tags);

    List<ProductAssociatedCategoryDTO> convertToProductAssociatedCategoryDTOList(List<ProductCategory> productCategoryList);
}
