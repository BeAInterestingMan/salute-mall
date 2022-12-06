package com.salute.mall.product.service.converter;

import com.salute.mall.product.service.pojo.dto.ProductBaseDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import com.salute.mall.product.service.pojo.entity.ProductSku;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductApiServiceConverter {
    List<ProductBaseDTO> convertToProductBaseDTOList(List<Product> productList);

    List<ProductSkuBaseDTO> convertToProductSkuBaseDTO(List<ProductSku> productSkuList);
}
