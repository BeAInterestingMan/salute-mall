package com.salute.mall.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    private Integer pageIndex;

    private Integer pageSize;

    private Long total;

    private List<T> data;
}
