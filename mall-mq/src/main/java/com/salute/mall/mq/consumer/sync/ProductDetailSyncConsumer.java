package com.salute.mall.mq.consumer.sync;

import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.api.client.ProductDetailInfoClient;
import com.salute.mall.product.api.response.ProductDetailInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductDetailSyncConsumer {

//    @Autowired
//    private ProductDetailInfoClient productDetailInfoClient;

    public void execute(){
        ArrayList<String> arrayList = Lists.newArrayList("");
//        for (String code : arrayList) {
//            Result<ProductDetailInfoResponse> detail = productDetailInfoClient.getProductDetail(code);
//            ProductDetailInfoResponse result = detail.getResult();
//
//        }
    }

}
