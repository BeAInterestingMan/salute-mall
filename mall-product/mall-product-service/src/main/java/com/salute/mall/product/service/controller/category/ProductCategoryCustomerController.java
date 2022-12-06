package com.salute.mall.product.service.controller.category;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.service.converter.ProductCategoryCustomerFaceConverter;
import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;
import com.salute.mall.product.service.pojo.response.ProductCategoryCustomerResponse;
import com.salute.mall.product.service.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product/customer/category/")
@Api(tags = "h5分类")
public class ProductCategoryCustomerController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductCategoryCustomerFaceConverter faceConverter;

    @GetMapping("getByCode")
    @ApiOperation("获取小程序的全部分类信息")
    public Result<List<ProductCategoryCustomerResponse>> getCategoryInfo(String categoryCode){
        List<ProductCategoryTreeDTO> productCategoryTreeDTOS = productCategoryService.queryAllCategoryInfo(categoryCode);
        List<ProductCategoryCustomerResponse> productCategoryList = faceConverter.convertToProductCategoryRespList(productCategoryTreeDTOS);
        return Result.success(productCategoryList);
    }
}
