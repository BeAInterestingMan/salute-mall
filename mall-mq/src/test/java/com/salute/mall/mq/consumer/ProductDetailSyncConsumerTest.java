package com.salute.mall.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.salute.mall.mq.MallMqApplication;
import com.salute.mall.mq.consumer.sync.ProductDetailSyncConsumer;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallMqApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductDetailSyncConsumerTest {

    @Autowired
    private ProductDetailSyncConsumer productDetailSyncConsumer;

    @Test
    public void searchProduct(){
        productDetailSyncConsumer.execute(Lists.newArrayList("1376373278360207360","1376529925690884096"));
        log.info(JSON.toJSONString(""));
    }

}
