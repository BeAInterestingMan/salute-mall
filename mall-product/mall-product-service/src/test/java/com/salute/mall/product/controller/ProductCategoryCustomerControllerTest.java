package com.salute.mall.product.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.MallProductApplication;
import com.salute.mall.product.service.controller.customer.ProductCategoryCustomerController;
import com.salute.mall.product.service.pojo.response.ProductCategoryCustomerResponse;
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
public class ProductCategoryCustomerControllerTest {

    @Autowired
    private ProductCategoryCustomerController productCategoryCustomerController;

    @Test
    public void searchProduct(){
        Result<List<ProductCategoryCustomerResponse>> categoryInfo = productCategoryCustomerController.getCategoryInfo("0");
        log.info(JSON.toJSONString(categoryInfo));
    }

}
