package com.salute.mall.ploy.service.test;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.ploy.MallPloyApplication;
import com.salute.mall.ploy.controller.cart.ShoppingCartController;
import com.salute.mall.ploy.pojo.response.ShoppingCartPloyResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MallPloyApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ShoppingCartControllerTest {

    @Autowired
    private ShoppingCartController shoppingCartController;

    @Test
    public void getUserShoppingCart(){
        Result<List<ShoppingCartPloyResponse>> userShoppingCart = shoppingCartController.getUserShoppingCart();
        log.info("execute searchProduct info:{}", JSON.toJSONString(userShoppingCart));
    }
    @Test
    public void deleteShoppingCartSku(){
        Result<Void> voidResult = shoppingCartController.deleteShoppingCartSku(null);
        log.info("execute searchProduct info:{}", JSON.toJSONString(voidResult));
    }
}
