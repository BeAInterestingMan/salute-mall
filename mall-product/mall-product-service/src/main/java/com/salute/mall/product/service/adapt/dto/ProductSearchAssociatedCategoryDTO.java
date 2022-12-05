package com.salute.mall.product.service.adapt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductSearchAssociatedCategoryDTO implements Serializable {

    private String categoryCodeThirdCode;

    private Long count;
}
