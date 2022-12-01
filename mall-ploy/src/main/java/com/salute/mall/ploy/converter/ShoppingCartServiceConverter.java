package com.salute.mall.ploy.converter;

import com.salute.mall.ploy.pojo.dto.ShoppingCartDetailDTO;
import com.salute.mall.ploy.pojo.entity.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartServiceConverter {
    ShoppingCartDetailDTO convertToShoppingCartDetailDTO(ShoppingCart shoppingCart);
}
