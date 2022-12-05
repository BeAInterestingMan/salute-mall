package com.salute.mall.product.service.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryProductPageDTO implements Serializable {

    private Integer pageIndex = 1;

    private Integer pageSize = 10;
}
