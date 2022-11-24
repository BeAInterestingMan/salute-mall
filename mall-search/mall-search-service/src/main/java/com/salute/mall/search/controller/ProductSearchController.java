package com.salute.mall.search.controller;

import com.salute.mall.search.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/search/product/")
public class ProductSearchController {

    @Autowired
    private ProductSearchService productSearchService;

    @PostMapping("detailList")
    public void searchProduct(@Valid QueryH5ProductPageRequest request){
        productSearchService.searchProduct(null);
    }
}
