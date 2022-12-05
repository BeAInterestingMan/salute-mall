package com.salute.mall.product.service.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAssociatedDTO implements Serializable {

   private List<ProductAssociatedBrandDTO> brandList;

   private List<ProductAssociatedCategoryDTO> categoryList;
}
