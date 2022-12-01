package com.salute.mall.product;

import com.alibaba.fastjson.JSON;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.pojo.dto.ProductSkuSpecificationDTO;
import com.salute.mall.product.service.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductInfoServiceTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void searchProduct(){
        ProductDetailInfoBO productDetail = productInfoService.getProductDetail("12545544787551");
        log.info("execute searchProduct info:{}", JSON.toJSONString(productDetail));
    }

    @Test
    public void getProductDetail(){
        ProductDetailInfoBO productDetail = productInfoService.getProductDetail("122343545544787551");
        log.info("execute searchProduct info:{}", JSON.toJSONString(productDetail));
    }
}
