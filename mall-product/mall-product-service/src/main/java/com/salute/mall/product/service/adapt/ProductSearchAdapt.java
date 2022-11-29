package com.salute.mall.product.service.adapt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.entity.Result;
import com.salute.mall.product.service.adapt.converter.ProductSearchAdaptConverter;
import com.salute.mall.product.service.adapt.dto.ProductListSearchDTO;
import com.salute.mall.product.service.adapt.dto.ProductListSearchResDTO;
import com.salute.mall.product.service.pojo.dto.ProductListInfoDTO;
import com.salute.mall.search.api.client.ProductSearchClient;
import com.salute.mall.search.api.pojo.request.QueryH5ProductPageRequest;
import com.salute.mall.search.api.pojo.response.ProductListSearchResponse;
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
    public Page<List<ProductListSearchResDTO>> searchProduct(ProductListInfoDTO infoDTO){
        log.info("execute searchProduct info,req:{}",JSON.toJSONString(infoDTO));
        QueryH5ProductPageRequest request = productSearchAdaptConverter.convertToQueryH5ProductPageRequest(infoDTO);
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
}
