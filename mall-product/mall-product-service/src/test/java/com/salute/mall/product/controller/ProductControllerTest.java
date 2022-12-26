package com.salute.mall.product.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.MallProductApplication;
import com.salute.mall.product.api.response.ProductDetailCustomInfoResponse;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.product.service.controller.product.ProductController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Test
    public void searchProduct(){
         productController.getProductSkuDetail("122343545544787551");
    }
}
