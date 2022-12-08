package com.salute.mall.product.service.controller.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.response.ProductDetailInfoResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.product.service.converter.ProductFaceConverter;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.bo.ProductListInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductAssociatedDTO;
import com.salute.mall.product.service.pojo.dto.ProductAssociatedQueryDTO;
import com.salute.mall.product.service.pojo.dto.ProductCustomerInfoQueryDTO;
import com.salute.mall.product.service.pojo.dto.ProductSkuDTO;
import com.salute.mall.product.service.pojo.request.ProductAssociatedRequest;
import com.salute.mall.product.service.pojo.request.ProductCustomerInfoRequest;
import com.salute.mall.product.service.pojo.response.ProductAssociatedResponse;
import com.salute.mall.product.service.pojo.response.ProductListInfoResponse;
import com.salute.mall.product.service.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/product/customer/")
@Api(tags = "小程序商品控制器")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFaceConverter productFaceConverter;


    @GetMapping("getProductDetail")
    @ApiOperation("获取小程序的商品详情信息")
    public Result<ProductDetailInfoResponse> getProductDetail(@NotBlank @RequestParam(name = "productCode") String productCode){
        log.info("execute getProductBySpuCode info,req:{}", JSON.toJSONString(productCode));
        ProductDetailInfoBO productDetail = productService.getProductDetail(productCode);
        ProductDetailInfoResponse response = productFaceConverter.convertToProductDetailInfoResponse(productDetail);
        log.info("execute getProductBySpuCode info,req:{},resp:{}", JSON.toJSONString(productCode), JSON.toJSONString(response));
        return Result.success(response);
    }

    @PostMapping("queryProductSkuDetail")
    @ApiOperation("根据skuCodeList获取sku商品详情信息")
    public Result<List<ProductSkuResponse>> queryProductSkuDetail(@NotEmpty @RequestBody List<String> skuCodeList){
        log.info("execute getProductBySpuCode info,req:{}", JSON.toJSONString(skuCodeList));
        List<ProductSkuDTO> ploySkuInfoDTOS = productService.queryProductSkuDetail(skuCodeList);
        List<ProductSkuResponse> response = productFaceConverter.convertToProductPloySkuInfoResponseList(ploySkuInfoDTOS);
        log.info("execute getProductBySpuCode info,req:{},resp:{}", JSON.toJSONString(skuCodeList), JSON.toJSONString(response));
        return Result.success(response);
    }

    @GetMapping("getProductSkuDetail")
    @ApiOperation("根据skuCode获取sku商品详情信息")
    public Result<ProductSkuResponse> getProductSkuDetail(@NotBlank @RequestParam(name = "skuCode") String skuCode){
        log.info("execute queryProductSkuDetail info,req:{}", JSON.toJSONString(skuCode));
        ProductSkuDTO ploySkuInfoDTO = productService.getProductSkuDetail(skuCode);
        ProductSkuResponse response = productFaceConverter.convertToProductPloySkuInfoResponse(ploySkuInfoDTO);
        log.info("execute queryProductSkuDetail info,req:{},resp:{}", JSON.toJSONString(skuCode), JSON.toJSONString(response));
        return Result.success(response);
    }


    @GetMapping("searchProduct")
    @ApiOperation("获取小程序商品列表详情")
    public Result<Page<List<ProductListInfoResponse>>> queryProductList(@Valid ProductCustomerInfoRequest request){
        log.info("execute queryProductList info,req:{}", JSON.toJSONString(request));
        ProductCustomerInfoQueryDTO infoDTO = productFaceConverter.convertToProductCustomerInfoDTO(request);
        Page<List<ProductListInfoBO>> page = productService.queryProductList(infoDTO);
        Page<List<ProductListInfoResponse>> responsePage =  productFaceConverter.convertToProductListInfoResponsePage(page);
        log.info("execute queryProductList info,req:{},resp:{}", JSON.toJSONString(request), JSON.toJSONString(responsePage));
        return Result.success(responsePage);
    }


    @GetMapping("/hotWords")
    @ApiOperation(value = "获取搜索热词")
    public Result<List<String>> getGoodsHotWords(Integer count) {
        return Result.success(Lists.newArrayList("测试","测试","测试","测试","测试","测试","测试","测试","测试","测试"));
    }

    @GetMapping("related")
    @ApiOperation("获取小程序搜索商品列表-相关的筛选分类 品牌")
    public Result<ProductAssociatedResponse> queryProductAssociatedInfo(@Valid ProductAssociatedRequest request){
        ProductAssociatedQueryDTO infoDTO = productFaceConverter.convertToProductAssociatedQueryDTO(request);
        ProductAssociatedDTO associatedDTO = productService.queryProductAssociatedInfo(infoDTO);
        ProductAssociatedResponse response = productFaceConverter.convertToProductAssociatedDTO(associatedDTO);
        return Result.success(response);
    }
}
