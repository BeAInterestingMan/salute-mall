package com.salute.mall.product.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.product.MallProductApplication;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void searchProduct(){
        ProductDetailInfoBO productDetail = productService.getProductDetail("12545544787551","");
        log.info("execute searchProduct info:{}", JSON.toJSONString(productDetail));
    }

    @Test
    public void getProductDetail(){
        ProductDetailInfoBO productDetail = productService.getProductDetail("122343545544787551","");
        log.info("execute searchProduct info:{}", JSON.toJSONString(productDetail));
    }
}
