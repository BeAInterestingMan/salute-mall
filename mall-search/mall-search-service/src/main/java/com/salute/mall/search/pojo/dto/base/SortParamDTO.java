package com.salute.mall.search.pojo.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  @Description es查询排序字段
 *  @author liuhu
 *  @Date 2022/11/24 9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortParamDTO implements Serializable {

    /**排序字段*/
    private String sortColumn;

    /**是否倒序*/
    private Boolean desc;
}
