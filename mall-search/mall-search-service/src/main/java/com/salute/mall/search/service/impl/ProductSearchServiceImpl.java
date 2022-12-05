package com.salute.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.converter.ProductSearchServiceConverter;
import com.salute.mall.search.enums.EsIndexEnums;
import com.salute.mall.search.helper.EsHelper;
import com.salute.mall.search.pojo.dto.base.BasePageParamDTO;
import com.salute.mall.search.pojo.dto.base.SortParamDTO;
import com.salute.mall.search.pojo.dto.product.ProductListSearchPageDTO;
import com.salute.mall.search.pojo.dto.product.ProductSearchAssociatedDTO;
import com.salute.mall.search.pojo.entity.EsAggBaseDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import com.salute.mall.search.pojo.entity.ProductSkuEsEntity;
import com.salute.mall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProductSearchServiceImpl implements ProductSearchService {

    @Autowired
    private EsHelper esHelper;

    @Autowired
    private ProductSearchServiceConverter productSearchServiceConverter;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Page<List<ProductEsEntity>> searchProduct(ProductListSearchPageDTO dto) {
        BoolQueryBuilder queryBuilder = buildBoolQuery(dto);
        BasePageParamDTO basePageParamDTO = buildProductBasePageParamDTO(dto);
        return esHelper.queryPage(queryBuilder, basePageParamDTO, ProductEsEntity.class);
    }

    @Override
    public List<EsAggBaseDTO> searchProductAssociated(ProductSearchAssociatedDTO dto, String groupKey, String groupName) {
        ProductListSearchPageDTO searchPageDTO =  productSearchServiceConverter.convertToProductListSearchPageDTO(dto);
        BoolQueryBuilder queryBuilder = buildBoolQuery(searchPageDTO);
        return esHelper.group(EsIndexEnums.PRODUCT.getName(), queryBuilder,groupKey,"brand");
    }

    @Override
    public void upsertProduct(ProductEsEntity productEsEntity) {
        esHelper.upsert(productEsEntity.getProductCode(), JSON.toJSONString(productEsEntity),EsIndexEnums.PRODUCT.getName());
    }

    /**
     * @Description 构建牌香烟
     * @author liuhu
     * @param dto
     * @date 2022/11/27 22:31
     * @return com.salute.mall.search.pojo.dto.base.BasePageParamDTO
     */
    private BasePageParamDTO buildProductBasePageParamDTO(ProductListSearchPageDTO dto) {
        ArrayList<SortParamDTO> sortParamDTOS = Lists.newArrayList();
        if(Objects.equals("saleNum",dto.getSort())){
            SortParamDTO paramDTO = SortParamDTO.builder()
                    .sortColumn(ProductEsEntity.Fields.saleNum)
                    .desc(Objects.equals(dto.getOrder(),"desc")).build();
            sortParamDTOS.add(paramDTO);
        }
        if(Objects.equals("salePrice",dto.getSort())){
            SortParamDTO paramDTO = SortParamDTO.builder()
                    .sortColumn(ProductEsEntity.Fields.productSku+"."+ProductSkuEsEntity.Fields.salePrice)
                    .desc(Objects.equals(dto.getOrder(),"desc")).build();
            sortParamDTOS.add(paramDTO);
        }
        SortParamDTO sortParamDTO = SortParamDTO.builder().sortColumn(ProductEsEntity.Fields.sort).desc(Boolean.TRUE).build();
        sortParamDTOS.add(sortParamDTO);
       return BasePageParamDTO.builder()
                .pageIndex(dto.getPageIndex())
                .pageSize(dto.getPageSize())
                .indexName(EsIndexEnums.PRODUCT.getName())
                .sortParamDTOList(sortParamDTOS)
                .build();
    }

    /**
     * @Description 构建查询条件
     * @author liuhu
     * @param dto
     * @date 2022/11/27 22:31
     * @return org.elasticsearch.index.query.BoolQueryBuilder
     */
    private BoolQueryBuilder buildBoolQuery(ProductListSearchPageDTO dto) {
         BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
         if(StringUtils.isNotBlank(dto.getKeyword())){
             queryBuilder.should(QueryBuilders.multiMatchQuery(dto.getKeyword(), ProductEsEntity.Fields.productName,
                     ProductEsEntity.Fields.keyword, ProductEsEntity.Fields.title,ProductEsEntity.Fields.brandName));
             queryBuilder.should(QueryBuilders.termQuery(dto.getKeyword(),ProductEsEntity.Fields.productCode));
             // 至少匹配一个
             queryBuilder.minimumShouldMatch(1);
         }
        if(StringUtils.isNotBlank(dto.getCategoryCode())){
            queryBuilder.must(QueryBuilders.termQuery(ProductEsEntity.Fields.categoryCodeThird,dto.getCategoryCode()));
        }
        if(StringUtils.isNotBlank(dto.getBrandCode())){
            queryBuilder.must(QueryBuilders.termQuery(ProductEsEntity.Fields.brandCode,dto.getBrandCode()));
        }
        if(Objects.nonNull(dto.getMinSalePrice()) && Objects.nonNull(dto.getMaxSalePrice())) {
            queryBuilder.must(QueryBuilders.rangeQuery(ProductEsEntity.Fields.productSku +"."+ ProductSkuEsEntity.Fields.salePrice).gt(dto.getMinSalePrice()).lt(dto.getMaxSalePrice()));
        }
        return queryBuilder;
    }
}
