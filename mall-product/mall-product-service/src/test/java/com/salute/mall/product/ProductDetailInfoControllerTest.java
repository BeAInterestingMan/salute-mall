package com.salute.mall.product;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.response.ProductPloySkuInfoResponse;
import com.salute.mall.product.service.controller.h5.ProductDetailInfoController;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductDetailInfoControllerTest {

    @Autowired
    private ProductDetailInfoController productDetailInfoController;

    @Test
    public void searchProduct(){
        Result<ProductPloySkuInfoResponse> skuDetail = productDetailInfoController.getProductSkuDetail("122343545544787551");
        log.info("execute searchProduct info:{}", JSON.toJSONString(skuDetail));
    }

}
