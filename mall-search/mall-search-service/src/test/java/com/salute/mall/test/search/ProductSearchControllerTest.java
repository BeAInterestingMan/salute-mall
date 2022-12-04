package com.salute.mall.test.search;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.MallSearchApplication;
import com.salute.mall.search.pojo.dto.product.ProductListSearchPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import com.salute.mall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MallSearchApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductSearchControllerTest {

    @Autowired
    private ProductSearchService productSearchService;

    @Test
    public void searchProduct(){
        ProductListSearchPageDTO dto = new ProductListSearchPageDTO();
//        dto.setKeyword("男装");
        dto.setSort("salePrice");
        dto.setOrder("desc");
        Page<List<ProductEsEntity>> listPage = productSearchService.searchProduct(dto);
        log.info("execute searchProduct info:{}", JSON.toJSONString(listPage));
    }
}
