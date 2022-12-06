package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.*;
import com.salute.mall.product.service.pojo.entity.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductServiceConverter {

    ProductBaseDTO convertToProductBaseInfoDTO(Product product);

    ProductDetailBaseDTO convertToProductDetailInfoDTO(ProductDetail productDetail);

    ProductSkuDTO convertToProductPloySkuInfoDTO(ProductSku sku);

    List<ProductTagBaseDTO> convertToProductTagBaseDTOList(List<ProductTag> tags);

    List<ProductAssociatedCategoryDTO> convertToProductAssociatedCategoryDTOList(List<ProductCategory> productCategoryList);
}
