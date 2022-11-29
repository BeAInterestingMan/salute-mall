package com.salute.mall.search.helper;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.core.entity.Page;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.search.pojo.dto.base.BasePageParamDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
}
