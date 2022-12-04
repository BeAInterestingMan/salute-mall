package com.salute.mall.product.service.adapt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductSearchAssociatedBrandDTO  implements Serializable {

    private String brandCode;

    private String brandName;

    private Long count;
}
