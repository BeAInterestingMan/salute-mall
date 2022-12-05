package com.salute.mall.product.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.MallProductApplication;
import com.salute.mall.product.api.response.ProductSkuResponse;
import com.salute.mall.product.service.controller.customer.ProductDetailInfoController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductDetailInfoControllerTest {

    @Autowired
    private ProductDetailInfoController productDetailInfoController;

    @Test
    public void searchProduct(){
        Result<ProductSkuResponse> skuDetail = productDetailInfoController.getProductSkuDetail("122343545544787551");
        log.info("execute searchProduct info:{}", JSON.toJSONString(skuDetail));
    }

}
