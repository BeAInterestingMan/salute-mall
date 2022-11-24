package com.salute.mall.product.service.controller.h5;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.service.converter.ProductCategoryFaceConverter;
import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;
import com.salute.mall.product.service.pojo.response.ProductCategoryResponse;
import com.salute.mall.product.service.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/h5/category/")
@Api(tags = "h5分类")
public class ProductCategoryH5Controller {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductCategoryFaceConverter faceConverter;

    @GetMapping("getAll")
    @ApiOperation("获取小程序的全部分类信息")
    public Result<List<ProductCategoryResponse>> getAllCategoryInfo(){
        List<ProductCategoryTreeDTO> productCategoryTreeDTOS = productCategoryService.queryAllCategoryInfo();
        List<ProductCategoryResponse> productCategoryList = faceConverter.convertToProductCategoryRespList(productCategoryTreeDTOS);
        return Result.success(productCategoryList);
    }
}
