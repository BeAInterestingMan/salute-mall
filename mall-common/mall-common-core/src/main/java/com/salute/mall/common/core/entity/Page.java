package com.salute.mall.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    private Integer pageIndex;

    private Integer pageSize;

    private Long total;

    private T data;

    public static Page emptyPage(int pageIndex,int pageSize){
        return new Page(pageIndex,pageSize,0L,new ArrayList<>());
    }
}
