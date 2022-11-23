package com.salute.mall.product.service.controller.h5;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.service.pojo.request.ProductListInfoRequest;
import com.salute.mall.product.service.pojo.response.ProductCategoryResp;
import com.salute.mall.product.service.pojo.response.ProductListInfoResponse;
import com.salute.mall.product.service.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public Result<ProductCategoryResp> getProductBySpuCode(@NotBlank @RequestParam(name = "spuCode") String spuCode){
        productInfoService.getProductByCategoryCode(spuCode);
        return Result.success();
    }

    @GetMapping("queryProductList")
    @ApiOperation("获取小程序商品列表详情")
    public Result<List<ProductListInfoResponse>> queryProductList(@Valid ProductListInfoRequest request){
//        productInfoService.queryProductList(request);
        return Result.success();
    }
}
