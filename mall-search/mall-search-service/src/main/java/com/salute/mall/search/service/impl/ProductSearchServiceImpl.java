package com.salute.mall.search.service.impl;

import com.google.common.collect.Lists;
import com.salute.mall.search.helper.EsHelper;
import com.salute.mall.search.pojo.dto.base.BasePageParamDTO;
import com.salute.mall.search.pojo.dto.base.SortParamDTO;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEntity;
import com.salute.mall.search.service.ProductSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    @Autowired
    private EsHelper esHelper;

    @Override
    public void searchProduct(QueryH5ProductPageDTO dto) {
        BoolQueryBuilder queryBuilder = buildBoolQuery(dto);
        BasePageParamDTO basePageParamDTO = buildProductBasePageParamDTO(dto);
        esHelper.queryPage(queryBuilder,basePageParamDTO,null);
    }

    private BasePageParamDTO buildProductBasePageParamDTO(QueryH5ProductPageDTO dto) {
        SortParamDTO.builder().sortColumn("").desc(Objects.equals(dto.getSortValue(),"DESC")?Boolean.TRUE:Boolean.FALSE).build();
        return BasePageParamDTO.builder()
                .pageIndex(dto.getPageIndex())
                .pageSize(dto.getPageSize())
                .indexName("")
                .sortParamDTOList(Lists.newArrayList())
                .build();
    }

    private BoolQueryBuilder buildBoolQuery(QueryH5ProductPageDTO dto) {
         BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
         if(StringUtils.isNotBlank(dto.getKeyword())){
             queryBuilder.should(QueryBuilders.multiMatchQuery(dto.getKeyword(), ProductEntity.Fields.skuName, ProductEntity.Fields.spuName, ProductEntity.Fields.sellPoint,ProductEntity.Fields.brandName));
             queryBuilder.should(QueryBuilders.termQuery(dto.getKeyword(),ProductEntity.Fields.skuCode));
             queryBuilder.should(QueryBuilders.termQuery(dto.getKeyword(),ProductEntity.Fields.spuCode));
             queryBuilder.minimumShouldMatch(1);
         }
        queryBuilder.must(QueryBuilders.termQuery(dto.getBrandCode(),ProductEntity.Fields.brandCode));
        queryBuilder.must(QueryBuilders.rangeQuery(ProductEntity.Fields.salePrice).gt(dto.getStartSalePrice()).lt(dto.getEndSalePrice()));
        return queryBuilder;
    }
}
