package com.salute.mall.order.service.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@ApiModel("加入购物车入参")
public class AddShoppingCartRequest implements Serializable {

    @ApiModelProperty(value = "sku编号",name = "skuCode")
    @NotBlank
    @Size(max = 30)
    private String skuCode;

    @ApiModelProperty(value = "购买数量",name = "num")
    @Max(100000)
    @Min(1)
    @NotNull
    private Integer num;
}
