package com.salute.mall.product.service.controller.h5;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.response.ProductDetailInfoResponse;
import com.salute.mall.product.api.response.ProductPloySkuInfoResponse;
import com.salute.mall.product.service.converter.ProductInfoH5Converter;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductListInfoDTO;
import com.salute.mall.product.service.pojo.dto.ProductPloySkuInfoDTO;
import com.salute.mall.product.service.pojo.request.ProductListInfoRequest;
import com.salute.mall.product.service.pojo.response.ProductListInfoResponse;
import com.salute.mall.product.service.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/h5/product/")
@Api(tags = "h5商品详情")
@Slf4j
public class ProductDetailInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductInfoH5Converter productInfoH5Converter;

    @GetMapping("getProductByCategoryCode")
    @ApiOperation("获取小程序的商品详情信息")
    public Result<ProductDetailInfoResponse> getProductByProductCode(@NotBlank @RequestParam(name = "productCode") String productCode){
        log.info("execute getProductBySpuCode info,req:{}", JSON.toJSONString(productCode));
        ProductDetailInfoBO productDetail = productInfoService.getProductDetail(productCode);
        ProductDetailInfoResponse response = productInfoH5Converter.convertToProductDetailInfoResponse(productDetail);
        log.info("execute getProductBySpuCode info,req:{},resp:{}", JSON.toJSONString(productCode), JSON.toJSONString(response));
        return Result.success(response);
    }

    @GetMapping("queryProductSkuDetail")
    @ApiOperation("根据skuCodeList获取sku商品详情信息")
    public Result<List<ProductPloySkuInfoResponse>> queryProductSkuDetail(@NotEmpty @RequestParam(name = "skuCodeList") List<String> skuCodeList){
        log.info("execute getProductBySpuCode info,req:{}", JSON.toJSONString(skuCodeList));
        List<ProductPloySkuInfoDTO> ploySkuInfoDTOS = productInfoService.queryProductSkuDetail(skuCodeList);
        List<ProductPloySkuInfoResponse> response = productInfoH5Converter.convertToProductPloySkuInfoResponseList(ploySkuInfoDTOS);
        log.info("execute getProductBySpuCode info,req:{},resp:{}", JSON.toJSONString(skuCodeList), JSON.toJSONString(response));
        return Result.success(response);
    }

    @GetMapping("queryProductSkuDetail")
    @ApiOperation("根据skuCode获取sku商品详情信息")
    public Result<ProductPloySkuInfoResponse> getProductSkuDetail(@NotBlank @RequestParam(name = "skuCode") String skuCode){
        log.info("execute queryProductSkuDetail info,req:{}", JSON.toJSONString(skuCode));
        ProductPloySkuInfoDTO ploySkuInfoDTO = productInfoService.getProductSkuDetail(skuCode);
        ProductPloySkuInfoResponse response = productInfoH5Converter.convertToProductPloySkuInfoResponse(ploySkuInfoDTO);
        log.info("execute queryProductSkuDetail info,req:{},resp:{}", JSON.toJSONString(skuCode), JSON.toJSONString(response));
        return Result.success(response);
    }

    @GetMapping("queryProductList")
    @ApiOperation("获取小程序商品列表详情")
    public Result<Page<List<ProductListInfoResponse>>> queryProductList(@Valid ProductListInfoRequest request){
        log.info("execute queryProductList info,req:{}", JSON.toJSONString(request));
        ProductListInfoDTO infoDTO = productInfoH5Converter.convertToProductListInfoDTO(request);
        Page<List<ProductListInfoBO>> page = productInfoService.queryProductList(infoDTO);
        Page<List<ProductListInfoResponse>> responsePage =  productInfoH5Converter.convertToProductListInfoResponsePage(page);
        log.info("execute queryProductList info,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(responsePage));
        return Result.success(responsePage);
    }
}
