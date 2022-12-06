package com.salute.mall.order.controller;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.order.service.MallOrderApplication;
import com.salute.mall.order.service.controller.SaleOrderCustomerController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallOrderApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SaleOrderCustomerControllerTest {

    @Autowired
    private SaleOrderCustomerController saleOrderCustomerController;

    @Test
    public void searchProduct(){
        for (int i = 0; i < 1000; i++) {
            Result<String> result = saleOrderCustomerController.getSaleOrderCode();
            log.info("orderCode:{}",result.getResult());
        }

    }
}
