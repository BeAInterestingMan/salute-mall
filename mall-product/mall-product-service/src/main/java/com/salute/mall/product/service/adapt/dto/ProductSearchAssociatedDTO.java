package com.salute.mall.product.service.adapt.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductSearchAssociatedDTO implements Serializable {

   private List<ProductSearchAssociatedBrandDTO> brandList;

   private List<ProductSearchAssociatedCategoryDTO> categoryList;
}
