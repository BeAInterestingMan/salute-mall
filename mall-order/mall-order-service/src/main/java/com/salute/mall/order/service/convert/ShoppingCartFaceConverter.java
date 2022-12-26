package com.salute.mall.order.service.convert;

import com.salute.mall.order.service.pojo.dto.cart.ShoppingCartPloyDTO;
import com.salute.mall.order.service.pojo.response.ShoppingCartPloyResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingCartFaceConverter {
    List<ShoppingCartPloyResponse> convertToShoppingCartDetailResponseList(List<ShoppingCartPloyDTO> userShoppingCartList);
}
