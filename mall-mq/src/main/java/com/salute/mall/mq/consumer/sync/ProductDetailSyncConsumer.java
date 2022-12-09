package com.salute.mall.mq.consumer.sync;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.mq.consumer.converter.ProductDetailSyncFaceConverter;
import com.salute.mall.product.api.client.ProductApiClient;
import com.salute.mall.product.api.response.*;
import com.salute.mall.search.api.client.ProductSearchClient;
import com.salute.mall.search.api.pojo.request.ProductSaveEsRequest;
import com.salute.mall.search.api.pojo.request.ProductSkuSaveEsRequest;
import com.salute.mall.search.api.pojo.request.ProductTagSaveEsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProductDetailSyncConsumer {

    @Autowired
    private ProductApiClient commonApiClient;

    @Autowired
    private ProductSearchClient productSearchClient;

    @Autowired
    private ProductDetailSyncFaceConverter productDetailSyncFaceConverter;

    public void execute(List<String> arrayList){
        for (String code : arrayList) {
            Result<ProductInfoResponse> result = commonApiClient.getProductDetail(code);
            if(Objects.isNull(result) || Objects.equals(result.isSuccess(),Boolean.FALSE)){
                return;
            }
            ProductInfoResponse productInfoResponse = result.getResult();
            ProductSaveEsRequest request =  buildProductSaveEsRequest(productInfoResponse);
            productSearchClient.saveEsProduct(request);
        }
    }

    private ProductSaveEsRequest buildProductSaveEsRequest( ProductInfoResponse productInfoResponse) {
        ProductBaseResponse productBaseResponse = productInfoResponse.getProductBaseInfo();
        ProductSaveEsRequest request = productDetailSyncFaceConverter.convertToProductSaveEsRequest(productBaseResponse);
        fillProductDetailInfo(request,productInfoResponse.getProductDetail());
        fillProductSkuInfoList(request,productInfoResponse.getProductSkuList());
        fillProductTagList(request,productInfoResponse.getProductTagBaseList());
        return request;
    }

    private void fillProductTagList(ProductSaveEsRequest request, List<ProductBaseTagResponse> productTagBaseList) {
        List<ProductTagSaveEsRequest> list =  productDetailSyncFaceConverter.convertToProductTagSaveEsRequestList(productTagBaseList);
        request.setProductTag(list);
    }

    private void fillProductSkuInfoList(ProductSaveEsRequest request, List<ProductSkuResponse> productPloySkuInfoList) {
       List<ProductSkuSaveEsRequest> list =  productDetailSyncFaceConverter.convertToProductSkuSaveEsRequestList(productPloySkuInfoList);
       request.setProductSku(list);
    }

    private void fillProductDetailInfo(ProductSaveEsRequest request, ProductDetailBaseResponse productDetailInfo) {
        if(Objects.isNull(productDetailInfo)){
            return;
        }
        request.setShelfLife(productDetailInfo.getShelfLife());
        request.setShelfLifeUnit(productDetailInfo.getShelfLifeUnit());
        request.setProductTime(productDetailInfo.getProductTime());
        request.setOriginPlace(productDetailInfo.getOriginPlace());
        request.setWeight(productDetailInfo.getWeight());
    }

}
