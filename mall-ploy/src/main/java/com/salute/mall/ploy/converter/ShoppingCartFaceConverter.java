package com.salute.mall.ploy.converter;

import com.salute.mall.ploy.pojo.dto.ShoppingCartPloyDTO;
import com.salute.mall.ploy.pojo.response.ShoppingCartDetailResponse;
import com.salute.mall.ploy.pojo.response.ShoppingCartPloyResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingCartFaceConverter {
    List<ShoppingCartPloyResponse> convertToShoppingCartDetailResponseList(List<ShoppingCartPloyDTO> userShoppingCartList);
}
