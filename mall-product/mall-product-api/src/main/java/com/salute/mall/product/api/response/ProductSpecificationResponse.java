package com.salute.mall.product.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *  @Description 商品规格信息
 *  @author liuhu
 *  @Date 2022/11/29 20:49
 */
@Data
public class ProductSpecificationResponse implements Serializable {

    @ApiModelProperty(value = "规格项名字")
    private String specName;

    @ApiModelProperty(value = "规格值")
    private String specValue;
}
