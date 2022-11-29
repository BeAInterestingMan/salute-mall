package com.salute.mall.search.service.impl;

import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.search.enums.EsIndexEnums;
import com.salute.mall.search.helper.EsHelper;
import com.salute.mall.search.pojo.dto.base.BasePageParamDTO;
import com.salute.mall.search.pojo.dto.base.SortParamDTO;
import com.salute.mall.search.pojo.dto.product.QueryH5ProductPageDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import com.salute.mall.search.pojo.entity.ProductSkuEsDTO;
import com.salute.mall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public Page<List<ProductEsEntity>> searchProduct(QueryH5ProductPageDTO dto) {
        BoolQueryBuilder queryBuilder = buildBoolQuery(dto);
        BasePageParamDTO basePageParamDTO = buildProductBasePageParamDTO(dto);
        return esHelper.queryPage(queryBuilder, basePageParamDTO, ProductEsEntity.class);
    }

    /**
     * @Description 构建牌香烟
     * @author liuhu
     * @param dto
     * @date 2022/11/27 22:31
     * @return com.salute.mall.search.pojo.dto.base.BasePageParamDTO
     */
    private BasePageParamDTO buildProductBasePageParamDTO(QueryH5ProductPageDTO dto) {
        SortParamDTO sortParamDTO = SortParamDTO.builder().sortColumn(ProductEsEntity.Fields.sort).desc(Boolean.TRUE).build();
        ArrayList<SortParamDTO> sortParamDTOS = Lists.newArrayList(sortParamDTO);
        if(Objects.equals("SALE_NUM",dto.getSortType())){
            SortParamDTO paramDTO = SortParamDTO.builder().sortColumn(ProductEsEntity.Fields.saleNum).desc(Boolean.TRUE).build();
            sortParamDTOS.add(paramDTO);
        }
        if(Objects.equals("SALE_PRICE",dto.getSortType())){
            SortParamDTO paramDTO = SortParamDTO.builder().sortColumn(ProductEsEntity.Fields.productSku+"."+ProductSkuEsDTO.Fields.salePrice).desc(Boolean.TRUE).build();
            sortParamDTOS.add(paramDTO);
        }
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
    private BoolQueryBuilder buildBoolQuery(QueryH5ProductPageDTO dto) {
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
        if(Objects.nonNull(dto.getStartSalePrice()) && Objects.nonNull(dto.getEndSalePrice())) {
            queryBuilder.must(QueryBuilders.rangeQuery(ProductEsEntity.Fields.productSku +"."+ ProductSkuEsDTO.Fields.salePrice).gt(dto.getStartSalePrice()).lt(dto.getEndSalePrice()));
        }
        return queryBuilder;
    }
}
