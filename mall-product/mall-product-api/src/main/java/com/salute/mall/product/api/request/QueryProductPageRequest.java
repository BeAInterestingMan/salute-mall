package com.salute.mall.product.api.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class QueryProductPageRequest implements Serializable {

    private Integer pageIndex = 1;

    private Integer pageSize = 10;
}
