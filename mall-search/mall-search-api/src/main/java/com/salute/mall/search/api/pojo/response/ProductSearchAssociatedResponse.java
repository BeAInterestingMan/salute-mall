package com.salute.mall.search.api.pojo.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class ProductSearchAssociatedResponse implements Serializable {

   private List<ProductAssociatedBrandResponse> brandList;

   private List<ProductAssociatedCategoryResponse> categoryList;
}
