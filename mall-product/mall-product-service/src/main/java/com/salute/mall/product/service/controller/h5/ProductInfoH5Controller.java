package com.salute.mall.product.service.controller.h5;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.product.service.converter.ProductCategoryFaceConverter;
import com.salute.mall.product.service.pojo.dto.ProductCategoryTreeDTO;
import com.salute.mall.product.service.pojo.response.ProductCategoryResp;
import com.salute.mall.product.service.service.ProductCategoryService;
import com.salute.mall.product.service.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/h5/product/")
@Api(tags = "h5商品详情")
public class ProductInfoH5Controller {

    @Autowired
    private ProductInfoService productInfoService;


    @GetMapping("getProductByCategoryCode")
    @ApiOperation("获取小程序的三级分类商品信息")
    public Result<List<ProductCategoryResp>> getProductByCategoryCode(@NotBlank @RequestParam(name = "categoryCode") String categoryCode){
        productInfoService.getProductByCategoryCode(categoryCode);
        return Result.success();
    }
}
