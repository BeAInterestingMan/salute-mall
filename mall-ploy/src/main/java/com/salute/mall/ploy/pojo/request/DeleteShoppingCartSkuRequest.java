package com.salute.mall.ploy.pojo.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("购物车删除sku入参")
public class DeleteShoppingCartSkuRequest implements Serializable {

    private List<String> skuCodeList;
}
