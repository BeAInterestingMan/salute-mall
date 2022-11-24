package com.salute.mall.search.pojo.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
/**
 *  @Description 查询通用字段
 *  @author liuhu
 *  @Date 2022/11/24 9:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasePageParamDTO implements Serializable {

    private Integer pageIndex = 1;

    private Integer pageSize = 10;

    /**索引名*/
    private String indexName;

    /**排序字段*/
    private List<SortParamDTO> sortParamDTOList;
}
