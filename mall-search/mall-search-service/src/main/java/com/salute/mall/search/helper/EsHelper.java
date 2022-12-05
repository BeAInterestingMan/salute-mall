package com.salute.mall.search.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.search.enums.EsIndexEnums;
import com.salute.mall.search.pojo.dto.base.BasePageParamDTO;
import com.salute.mall.search.pojo.entity.EsAggBaseDTO;
import com.salute.mall.search.pojo.entity.ProductEsEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  @Description es通用分页
 *  @author liuhu
 *  @Date 2022/11/24 10:05
 */
@Component
@Slf4j
public class EsHelper {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @Description 通用分页查询
     * @author liuhu
     * @param queryBuilder
     * @param basePageParamDTO
     * @param target
     * @date 2022/11/24 10:04
     * @return com.salute.mall.common.core.entity.Page<T>
     */
    public <T> Page<List<T>> queryPage(BoolQueryBuilder queryBuilder, BasePageParamDTO basePageParamDTO, Class<T> target){
        log.info("execute queryPage info,queryBuilder:{},basePageParamDTO:{}",queryBuilder.toString(),JSON.toJSONString(basePageParamDTO));
        SearchSourceBuilder sourceBuilder = buildSearchSourceBuilder(queryBuilder, basePageParamDTO);
        log.info("execute queryPage info,sourceBuilder:{}",sourceBuilder.toString());
        SearchRequest searchRequest = new SearchRequest(basePageParamDTO.getIndexName());
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            long total = response.getHits().getTotalHits().value;
            SearchHit[] hits = response.getHits().getHits();
            List<T> list = Arrays.stream(hits).map(v -> JSON.parseObject(v.getSourceAsString(), target)).collect(Collectors.toList());
            return  new Page<>(basePageParamDTO.getPageIndex(),basePageParamDTO.getPageSize(),total,list);
        } catch (IOException e) {
            log.error("execute queryPage error,sourceBuilder:{}",sourceBuilder.toString(),e);
            throw new BusinessException("500","获取es分页异常");
        }
    }

    private SearchSourceBuilder buildSearchSourceBuilder(BoolQueryBuilder queryBuilder,BasePageParamDTO basePageParamDTO){
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);
        buildCommonPage(sourceBuilder,basePageParamDTO);
        //跟踪真实命中数量
        sourceBuilder.trackTotalHits(true);
        return sourceBuilder;
    }

    /**
     * @Description 构建通用分页
     * @author liuhu
     * @param sourceBuilder
     * @param basePageParamDTO
     * @date 2022/11/24 10:04
     * @return void
     */
    private void buildCommonPage(SearchSourceBuilder sourceBuilder,BasePageParamDTO basePageParamDTO){
        if(CollectionUtils.isEmpty(basePageParamDTO.getSortParamDTOList())){
           return;
        }
        // 构建排序值
        basePageParamDTO.getSortParamDTOList().forEach(v->{
            sourceBuilder.sort(v.getSortColumn(), Objects.equals(v.getDesc(),Boolean.TRUE)? SortOrder.DESC:SortOrder.ASC);
        });
        // 构建分页参数 es 从0开始
        sourceBuilder.from((basePageParamDTO.getPageIndex() - 1) * basePageParamDTO.getPageSize()).size(basePageParamDTO.getPageSize());
    }


    public List<EsAggBaseDTO> group(String index,BoolQueryBuilder queryBuilder,String groupKey,String groupName) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms(groupName).field(groupKey);
        searchSourceBuilder.aggregation(aggregationBuilder).query(queryBuilder).size(10);
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        List<EsAggBaseDTO> list = new ArrayList<>();
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Terms oneTerm = (Terms) response.getAggregations().asMap().get(groupName);
            for (Terms.Bucket bucket : oneTerm.getBuckets()) {
                String[] split = bucket.getKey().toString().split("_");
                EsAggBaseDTO baseDTO = EsAggBaseDTO.builder().code(split[0])
                        .count(bucket.getDocCount()).build();
                list.add(baseDTO);
            }
        } catch (Exception e) {
            log.error("execute es group error",e);
        }
        return list;
    }

    /**
     * @Description 新增记录 有这个记录则更新 无则新增
     * @author liuhu
     * @param id
     * @param doc
     * @param indexName
     * @date 2022/12/5 16:40
     * @return void
     */
    public void upsert(String id,String doc,String indexName){
        log.info("execute upsert success,indexName:{},id:{},doc:{}",indexName,id,doc);
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(id,doc,indexName),"参数异常");
        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(id)
                .source(doc, XContentType.JSON);
        UpdateRequest updateRequest = new UpdateRequest(indexName, id)
                .doc(doc, XContentType.JSON)
                .upsert(indexRequest);
        try {
             restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
             log.info("execute upsert success,indexName:{},id:{},doc:{}",indexName,id,doc);
        } catch (Exception e) {
           log.error("execute upsert error,indexName:{},id:{}",indexName,id,e);
        }
    }
}
