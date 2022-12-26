package com.salute.mall.order.service.convert;


import com.salute.mall.order.service.pojo.dto.cart.ProductSkuSpecificationPloyDTO;
import com.salute.mall.order.service.pojo.dto.cart.ShoppingCartDetailDTO;
import com.salute.mall.order.service.pojo.entity.ShoppingCart;
import com.salute.mall.product.api.response.ProductSkuSpecificationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingCartServiceConverter {
    ShoppingCartDetailDTO convertToShoppingCartDetailDTO(ShoppingCart shoppingCart);

    List<ProductSkuSpecificationPloyDTO> convertToProductSkuSpecificationPloyDTOList(List<ProductSkuSpecificationResponse> skuSpecificationList);
}
