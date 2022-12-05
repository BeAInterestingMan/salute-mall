package com.salute.mall.product.service.adapt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.service.adapt.converter.ProductSearchAdaptConverter;
import com.salute.mall.product.service.adapt.dto.ProductListSearchResDTO;
import com.salute.mall.product.service.adapt.dto.ProductSearchAssociatedDTO;
import com.salute.mall.product.service.pojo.dto.ProductAssociatedQueryDTO;
import com.salute.mall.product.service.pojo.dto.ProductCustomerInfoQueryDTO;
import com.salute.mall.search.api.client.ProductSearchClient;
import com.salute.mall.search.api.pojo.request.ProductListSearchPageRequest;
import com.salute.mall.search.api.pojo.request.ProductSearchAssociatedRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
import com.salute.mall.search.api.pojo.response.ProductSearchAssociatedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ProductSearchAdapt {


    @Autowired
    private ProductSearchClient productSearchClient;

    @Autowired
    private ProductSearchAdaptConverter productSearchAdaptConverter;

    /**
     * @Description 查询
     * @author liuhu
     * @param infoDTO
     * @date 2022/11/29 14:39
     * @return com.salute.mall.common.core.entity.Page<com.salute.mall.common.core.entity.Page<java.util.List<com.salute.mall.product.service.adapt.dto.ProductListSearchResDTO>>>
     */
    public Page<List<ProductListSearchResDTO>> searchProduct(ProductCustomerInfoQueryDTO infoDTO){
        log.info("execute searchProduct info,req:{}",JSON.toJSONString(infoDTO));
        ProductListSearchPageRequest request = productSearchAdaptConverter.convertToProductListSearchPageRequest(infoDTO);
        Result<Page<List<ProductListSearchResponse>>> result = productSearchClient.searchProduct(request);
        if(Objects.isNull(result) || Objects.equals(result.isStatus(),Boolean.FALSE)){
            log.error("execute searchProduct info,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(result));
            return new Page(infoDTO.getPageIndex(),infoDTO.getPageSize(),0L, Lists.newArrayList());
        }
        if(Objects.isNull(result.getResult())){
            log.warn("execute searchProduct has empty,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(result));
            return new Page(infoDTO.getPageIndex(),infoDTO.getPageSize(),0L, Lists.newArrayList());
        }
        Page<List<ProductListSearchResponse>> listPage = result.getResult();
        log.info("execute searchProduct success,req:{},resp:{}",JSON.toJSONString(infoDTO),JSON.toJSONString(result));
        return productSearchAdaptConverter.convertToProductListSearchResDTO(listPage);
    }

    public ProductSearchAssociatedDTO searchProductAssociated(ProductAssociatedQueryDTO infoDTO){
        log.info("execute searchProductAssociated info,req:{}",JSON.toJSONString(infoDTO));
        ProductSearchAssociatedRequest request = productSearchAdaptConverter.convertToProductSearchAssociatedRequest(infoDTO);
        Result<ProductSearchAssociatedResponse> result = productSearchClient.searchProductAssociated(request);
        if(Objects.isNull(result) || Objects.equals(result.isStatus(),Boolean.FALSE)){
            log.error("execute searchProductAssociated info,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(result));
            return null;
        }
        if(Objects.isNull(result.getResult())){
            log.warn("execute searchProductAssociated has empty,req:{},resp:{}", JSON.toJSONString(request),JSON.toJSONString(result));
            return null;
        }
        ProductSearchAssociatedResponse data = result.getResult();
        log.info("execute searchProductAssociated success,req:{},resp:{}",JSON.toJSONString(infoDTO),JSON.toJSONString(result));
        return productSearchAdaptConverter.convertToProductSearchAssociatedDTO(data);
    }
}
