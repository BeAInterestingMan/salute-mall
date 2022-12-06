package com.salute.mall.product.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.MallProductApplication;
import com.salute.mall.product.api.request.OperateFreezeStockRequest;
import com.salute.mall.product.api.request.OperateFreezeStockSkuRequest;
import com.salute.mall.product.service.controller.stock.ProductStockApiController;
import com.salute.mall.product.service.pojo.bo.ProductDetailInfoBO;
import com.salute.mall.product.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallProductApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductStockApiControllerTest {

    @Autowired
    private ProductStockApiController productStockApiController;

    @Test
    public void getProductDetail(){
        OperateFreezeStockRequest request = new OperateFreezeStockRequest();
        request.setBizCode("xqxqxqxqxq");
        request.setOperateCode("xaxa");
        request.setOperator("qqq");
        OperateFreezeStockSkuRequest request1 = new OperateFreezeStockSkuRequest();
        request1.setProductCode("xaxaa");
        request1.setSkuCode("1387977574864388096");
        request1.setSkuName("xaxa");
        request1.setStockNum(1);
        OperateFreezeStockSkuRequest request2 = new OperateFreezeStockSkuRequest();
        request2.setProductCode("xaxaxa");
        request2.setSkuCode("1387977447487569920");
        request2.setSkuName("xaxaxax");
        request2.setStockNum(1);
        request.setSkuStockList(Lists.newArrayList(request1,request2));
        Result<Void> result = productStockApiController.operateFreezeStock(request);
        log.info("execute searchProduct info:{}", JSON.toJSONString(result));
    }
}
