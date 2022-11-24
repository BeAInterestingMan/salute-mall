package com.salute.mall.search.pojo.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldNameConstants
public class ProductEntity implements Serializable {


    private String spuCode;

    private String spuName;

    private String brandCode;

    private String brandName;

    private BigDecimal salePrice;

    private String sellPoint;

    private String skuName;

    private String skuCode;
}
