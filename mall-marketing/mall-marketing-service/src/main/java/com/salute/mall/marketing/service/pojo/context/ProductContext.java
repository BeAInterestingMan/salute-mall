package com.salute.mall.marketing.service.pojo.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ProductContext implements Serializable {

    @ApiModelProperty(value = "商品sku编码",name = "skuCode")
    @NotBlank
    private String productCode;

    @ApiModelProperty(value = "门店编号",name = "shopCode")
    @NotBlank
    private String shopCode;

    @ApiModelProperty(value = "商品sku编码",name = "skuCode")
    @NotBlank
    private String skuCode;

    @ApiModelProperty(value = "三级分类编号",name = "categoryCodeThird")
    @NotBlank
    private String categoryCodeThird;
}
