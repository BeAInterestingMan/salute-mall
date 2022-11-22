package com.salute.mall.product.service.controller;

import com.salute.mall.product.service.entity.ProductSpu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "商品spu")
@RequestMapping("/product/spu")
public class ProductSpuController {

    @GetMapping
    @ApiOperation(value = "获取商品spu信息")
    @ApiImplicitParam(name = "spuCode", value = "spuCode", required = true)
    public ProductSpu getSpuBySpuCode(String spuCode){
        return null;
    }
}
