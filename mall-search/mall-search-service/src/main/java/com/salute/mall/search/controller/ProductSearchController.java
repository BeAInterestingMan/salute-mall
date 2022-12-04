package com.salute.mall.search.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.search.api.pojo.request.ProductListSearchPageRequest;
import com.salute.mall.search.api.pojo.request.ProductSearchAssociatedRequest;
import com.salute.mall.search.api.pojo.response.ProductAssociatedBrandResponse;
import com.salute.mall.search.api.pojo.response.ProductAssociatedCategoryResponse;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import com.salute.mall.search.api.pojo.response.ProductSearchAssociatedResponse;
import com.salute.mall.search.converter.ProductSearchFaceConverter;
import com.salute.mall.search.pojo.dto.product.ProductListSearchPageDTO;
import com.salute.mall.search.pojo.dto.product.ProductSearchAssociatedDTO;
import com.salute.mall.search.pojo.entity.EsAggBaseDTO;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/search/product/")
@Slf4j
public class ProductSearchController {

    @Autowired
    private ProductSearchFaceConverter productSearchFaceConverter;

    @Autowired
    private ProductSearchService productSearchService;

    @PostMapping("detailList")
    public Result<Page<List<ProductListSearchResponse>>> searchProduct(@Valid @RequestBody ProductListSearchPageRequest request){
        log.info("execute searchProduct info,req:{}", JSON.toJSONString(request));
        ProductListSearchPageDTO dto = productSearchFaceConverter.convertToProductListSearchPageRequest(request);
        Page<List<ProductEsEntity>> esEntityPage = productSearchService.searchProduct(dto);
        Page<List<ProductListSearchResponse>> response = productSearchFaceConverter.convertToProductListPageResponsePage(esEntityPage);
        log.info("execute searchProduct info,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(response));
        return Result.success(response);
    }

    @PostMapping("associated")
    public Result<ProductSearchAssociatedResponse> searchProductAssociated(@Valid @RequestBody ProductSearchAssociatedRequest request){
        log.info("execute searchProduct info,req:{}", JSON.toJSONString(request));
        ProductSearchAssociatedDTO dto = productSearchFaceConverter.convertToProductSearchAssociatedDTO(request);
        List<EsAggBaseDTO> brandAggBaseDTOS = productSearchService.searchProductAssociated(dto,ProductEsEntity.Fields.brandCode,"brand");
        List<EsAggBaseDTO> categoryEsAggBaseDTO = productSearchService.searchProductAssociated(dto, ProductEsEntity.Fields.categoryCodeThird,"category");
        ProductSearchAssociatedResponse response = buildProductSearchAssociatedResponse(brandAggBaseDTOS, categoryEsAggBaseDTO);
        log.info("execute searchProduct info,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(response));
        return Result.success(response);
    }

    private ProductSearchAssociatedResponse buildProductSearchAssociatedResponse(List<EsAggBaseDTO> brandAggBaseDTOS, List<EsAggBaseDTO> categoryEsAggBaseDTO) {
        List<ProductAssociatedBrandResponse> productAssociatedBrandResponseList = brandAggBaseDTOS.stream().map(v -> {
            ProductAssociatedBrandResponse response = new ProductAssociatedBrandResponse();
            response.setBrandCode(v.getCode());
            response.setCount(v.getCount());
            return response;
        }).collect(Collectors.toList());
        List<ProductAssociatedCategoryResponse> productAssociatedCategoryResponseList = categoryEsAggBaseDTO.stream().map(v -> {
            ProductAssociatedCategoryResponse response = new ProductAssociatedCategoryResponse();
            response.setCategoryCodeThirdCode(v.getCode());
            response.setCount(v.getCount());
            return response;
        }).collect(Collectors.toList());
        ProductSearchAssociatedResponse response = new ProductSearchAssociatedResponse();
        response.setBrandList(productAssociatedBrandResponseList);
        response.setCategoryList(productAssociatedCategoryResponseList);
        return response;
    }
}
