package com.salute.mall.product.service.pojo.response;

import com.salute.mall.product.service.pojo.dto.ProductAssociatedBrandDTO;
import com.salute.mall.product.service.pojo.dto.ProductAssociatedCategoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("商品搜索关联分类-品牌")
public class ProductAssociatedResponse implements Serializable {

   @ApiModelProperty(value = "品牌信息",name = "brandList")
   private List<ProductAssociatedBrandDTO> brandList;

   @ApiModelProperty(value = "分类信息",name = "categoryList")
   private List<ProductAssociatedCategoryDTO> categoryList;
}
