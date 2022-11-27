package com.salute.mall.search.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.converter.ProductSearchFaceConverter;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import com.salute.mall.search.pojo.reponse.ProductListPageResponse;
import com.salute.mall.search.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/search/product/")
@Slf4j
public class ProductSearchController {

    @Autowired
    private ProductSearchFaceConverter productSearchFaceConverter;

    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping("detailList")
    public Page<ProductListPageResponse> searchProduct(@Valid QueryH5ProductPageRequest request){
        log.info("execute searchProduct info,req:{}", JSON.toJSONString(request));
        QueryH5ProductPageDTO dto = productSearchFaceConverter.convertToQueryH5ProductPageDTO(request);
        Page<ProductEsEntity> esEntityPage = productSearchService.searchProduct(dto);
        Page<ProductListPageResponse> response = productSearchFaceConverter.convertToProductListPageResponsePage(esEntityPage);
        log.info("execute searchProduct info,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(response));
        return response;
    }
}
