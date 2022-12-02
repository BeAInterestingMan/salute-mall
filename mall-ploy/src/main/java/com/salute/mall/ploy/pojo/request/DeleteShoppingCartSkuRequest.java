package com.salute.mall.ploy.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("购物车删除sku入参")
public class DeleteShoppingCartSkuRequest implements Serializable {

    @ApiModelProperty(value = "sku",name = "skuCodeList")
    @NotEmpty
    private List<String> skuCodeList;
}
