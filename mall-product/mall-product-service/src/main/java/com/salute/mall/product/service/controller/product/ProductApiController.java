package com.salute.mall.product.service.controller.product;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.datasource.utils.PageUtil;
import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.*;
import com.salute.mall.product.service.converter.ProductApiFaceConverter;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductSkuBaseDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import com.salute.mall.product.service.service.ProductApiService;
import com.salute.mall.product.service.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("product/api/")
@Api(tags = "商品通用api接口")
@Slf4j
public class ProductApiController {

    @Autowired
    private ProductApiService productApiService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductApiFaceConverter productApiFaceConverter;

    @GetMapping("queryProductPage")
    public Result<Page<List<ProductResponse>>> queryProductPage(QueryProductPageRequest request){
        log.info("execute queryProductPage req:{}", JSON.toJSONString(request));
        QueryProductPageDTO queryProductPageDTO = productApiFaceConverter.convertToQueryProductPageDTO(request);
        IPage<Product> page = productApiService.queryProductPage(queryProductPageDTO);
        Page<List<ProductResponse>> listPage = PageUtil.convertToPage(page, ProductResponse.class);
        return Result.success(listPage);
    }

    @GetMapping("queryProductSkuList")
    public Result<List<ProductSkuResponse>> queryProductSkuList(List<String> productCodeList){
        log.info("execute queryProductList req:{}", JSON.toJSONString(productCodeList));
        List<ProductSkuBaseDTO> productSkuBaseDTOList = productApiService.queryProductSkuList(productCodeList);
        List<ProductSkuResponse> productResponseList = productApiFaceConverter.convertToProductSkuResponse(productSkuBaseDTOList);
        log.info("execute queryProductList req:{} resp:{}", JSON.toJSONString(productCodeList),JSON.toJSONString(productResponseList));
        return Result.success(productResponseList);
    }

    @GetMapping("getProductDetail")
    @ApiOperation("获取小程序的商品详情信息")
    public Result<ProductInfoResponse> getProductDetail(@NotBlank @RequestParam(name = "productCode") String productCode){
        log.info("execute getProductBySpuCode info,req:{}", JSON.toJSONString(productCode));
        ProductDetailInfoBO productDetail = productService.getProductDetail(productCode);
        ProductInfoResponse response = productApiFaceConverter.convertToProductInfoResponse(productDetail);
        log.info("execute getProductBySpuCode info,req:{},resp:{}", JSON.toJSONString(productCode), JSON.toJSONString(response));
        return Result.success(response);
    }
}
