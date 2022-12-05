package com.salute.mall.product.service.service.impl;

import com.salute.mall.product.service.converter.ProductCategoryServiceConverter;
import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;
import com.salute.mall.product.service.pojo.entity.ProductCategory;
import com.salute.mall.product.service.repository.ProductCategoryRepository;
import com.salute.mall.product.service.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryServiceConverter categoryServiceConverter;

    @Override
    public List<ProductCategoryTreeDTO> queryAllCategoryInfo(String categoryCode) {
        List<ProductCategory> allCategoryList = productCategoryRepository.queryAllEnableCategoryList();
        List<ProductCategoryTreeDTO> categoryTreeDTOS = allCategoryList.stream().filter(productCategory -> Objects.equals(productCategory.getParentCategoryCode(), categoryCode ))
                .map(parentCategory -> {
                    ProductCategoryTreeDTO parentCategoryTreeDTO = categoryServiceConverter.convertToProductCategoryTreeDTO(parentCategory);
                    // 递归获取一级分类的子分类
                    List<ProductCategoryTreeDTO> childrenCategoryTreeDTOS = buildChildrenCategory(parentCategory, allCategoryList);
                    parentCategoryTreeDTO.setChildrenList(childrenCategoryTreeDTOS);
                    return parentCategoryTreeDTO;
                }).collect(Collectors.toList());
        return categoryTreeDTOS;
    }

    /**
     * @Description 递归获取分类的子分类
     * @author liuhu
     * @param parentCategory
     * @param allCategoryList
     * @date 2022/11/23 14:02
     * @return java.util.List<com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO>
     */
    private List<ProductCategoryTreeDTO> buildChildrenCategory(ProductCategory parentCategory, List<ProductCategory> allCategoryList) {
       return allCategoryList.stream()
                .filter(productCategory -> Objects.equals(parentCategory.getCategoryCode(),productCategory.getParentCategoryCode()))
                .map(v->{
                    ProductCategoryTreeDTO productCategoryTreeDTO = categoryServiceConverter.convertToProductCategoryTreeDTO(v);
                    // 递归获取子分类的子分类
                    List<ProductCategoryTreeDTO> childrenCategory = buildChildrenCategory(v,allCategoryList);
                    productCategoryTreeDTO.setChildrenList(childrenCategory);
                    return productCategoryTreeDTO;
                }).collect(Collectors.toList());
    }
}
