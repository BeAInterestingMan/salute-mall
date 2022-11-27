package com.salute.mall.test.search;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.SearchApplication;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import com.salute.mall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductSearchServiceTest {

    @Autowired
    private ProductSearchService productSearchService;

    @Test
    public void searchProduct(){
        QueryH5ProductPageDTO dto = new QueryH5ProductPageDTO();
//        dto.setKeyword("男装");
        dto.setSortType("SALE_PRICE");
        dto.setSortValue("DESC");
        Page<ProductEsEntity> esEntityPage = productSearchService.searchProduct(dto);
        log.info("execute searchProduct info:{}", JSON.toJSONString(esEntityPage));
    }
}
