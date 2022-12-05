package com.salute.mall.product.service.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductAssociatedBrandDTO  implements Serializable {

    private String brandCode;

    private String brandName;
}
