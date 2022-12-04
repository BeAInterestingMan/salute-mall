package com.salute.mall.test.search;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.search.MallSearchApplication;
import com.salute.mall.search.api.pojo.request.ProductListSearchPageRequest;
import com.salute.mall.search.api.pojo.request.ProductSearchAssociatedRequest;
import com.salute.mall.search.api.pojo.response.ProductSearchAssociatedResponse;
import com.salute.mall.search.controller.ProductSearchController;
import com.salute.mall.search.pojo.dto.product.ProductListSearchPageDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallSearchApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductSearchServiceTest {

    @Autowired
    private ProductSearchController productSearchController;

    @Test
    public void searchProduct(){
        ProductSearchAssociatedRequest dto = new ProductSearchAssociatedRequest();
//        dto.setKeyword("男装");
        Result<ProductSearchAssociatedResponse> responseResult = productSearchController.searchProductAssociated(dto);
        log.info("execute searchProduct info:{}", JSON.toJSONString(responseResult));
    }
}
