package com.salute.mall.ploy.converter;

import com.salute.mall.ploy.pojo.dto.ProductSkuSpecificationPloyDTO;
import com.salute.mall.ploy.pojo.dto.ShoppingCartDetailDTO;
import com.salute.mall.ploy.pojo.entity.ShoppingCart;
import com.salute.mall.product.api.response.ProductSkuSpecificationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingCartServiceConverter {
    ShoppingCartDetailDTO convertToShoppingCartDetailDTO(ShoppingCart shoppingCart);

    List<ProductSkuSpecificationPloyDTO> convertToProductSkuSpecificationPloyDTOList(List<ProductSkuSpecificationResponse> skuSpecificationList);
}
