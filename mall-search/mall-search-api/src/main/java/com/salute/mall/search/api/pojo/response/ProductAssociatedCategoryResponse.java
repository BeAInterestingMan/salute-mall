package com.salute.mall.search.api.pojo.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductAssociatedCategoryResponse implements Serializable {

    private String categoryCodeThirdName;

    private String categoryCodeThirdCode;

    private Long count;
}
