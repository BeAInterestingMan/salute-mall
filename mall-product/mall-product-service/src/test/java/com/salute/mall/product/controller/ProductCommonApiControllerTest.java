package com.salute.mall.product.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.MallProductApplication;
import com.salute.mall.product.api.request.QueryProductPageRequest;
import com.salute.mall.product.api.response.ProductInfoResponse;
import com.salute.mall.product.service.controller.product.ProductApiController;
import com.salute.mall.product.api.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MallProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductCommonApiControllerTest {

    @Autowired
    private ProductApiController commonApiController;

    @Test
    public void searchProduct(){
        QueryProductPageRequest request = new QueryProductPageRequest();
        request.setPageIndex(1);
        request.setPageSize(10);
        Result<Page<List<ProductResponse>>> result = commonApiController.queryProductPage(request);
        log.info(JSON.toJSONString(result));
    }

    @Test
    public void getProductDetail(){
        Result<ProductInfoResponse> productDetail = commonApiController.getProductDetail("1376373278360207360");
        log.info(JSON.toJSONString(productDetail));
    }
}
