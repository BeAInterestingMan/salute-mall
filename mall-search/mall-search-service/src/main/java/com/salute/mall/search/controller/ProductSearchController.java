package com.salute.mall.search.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.search.api.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import com.salute.mall.search.converter.ProductSearchFaceConverter;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import com.salute.mall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/search/product/")
@Slf4j
public class ProductSearchController {

    @Autowired
    private ProductSearchFaceConverter productSearchFaceConverter;

    @Autowired
    private ProductSearchService productSearchService;

    @PostMapping("detailList")
    public Result<Page<List<ProductListSearchResponse>>> searchProduct(@Valid @RequestBody QueryH5ProductPageRequest request){
        log.info("execute searchProduct info,req:{}", JSON.toJSONString(request));
        QueryH5ProductPageDTO dto = productSearchFaceConverter.convertToQueryH5ProductPageDTO(request);
        Page<List<ProductEsEntity>> esEntityPage = productSearchService.searchProduct(dto);
        Page<List<ProductListSearchResponse>> response = productSearchFaceConverter.convertToProductListPageResponsePage(esEntityPage);
        log.info("execute searchProduct info,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(response));
        return Result.success(response);
    }
}
