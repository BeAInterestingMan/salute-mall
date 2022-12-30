package com.salute.mall.product.service.controller.product;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.datasource.utils.PageUtil;
import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductResponse;
import com.salute.mall.product.api.response.ProductSkuBaseInfoResponse;
import com.salute.mall.product.service.converter.ProductApiFaceConverter;
import com.salute.mall.product.service.pojo.dto.ProductSkuDTO;
import com.salute.mall.product.service.pojo.dto.QueryProductPageDTO;
import com.salute.mall.product.service.pojo.entity.Product;
import com.salute.mall.product.service.service.ProductApiService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product/api/")
@Api(tags = "商品通用api接口")
@Slf4j
public class ProductApiController {

    @Autowired
    private ProductApiService productApiService;

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
    public Result<List<ProductSkuBaseInfoResponse>> queryProductSkuList(List<String> productCodeList){
        log.info("execute queryProductList req:{}", JSON.toJSONString(productCodeList));
        List<ProductSkuDTO> productSkuDTOS = productApiService.queryProductSkuList(productCodeList);
        List<ProductSkuBaseInfoResponse> productResponseList = productApiFaceConverter.convertToProductSkuBaseInfoResponse(productSkuDTOS);
        log.info("execute queryProductList req:{} resp:{}", JSON.toJSONString(productCodeList),JSON.toJSONString(productResponseList));
        return Result.success(productResponseList);
    }
}
