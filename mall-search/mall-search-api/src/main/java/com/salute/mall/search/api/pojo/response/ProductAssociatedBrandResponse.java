package com.salute.mall.search.api.pojo.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductAssociatedBrandResponse  implements Serializable {

    private String brandCode;

    private Long count;
}
