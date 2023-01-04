package com.salute.mall.order.service.adapt.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
@Data
public class OperateFreezeStockAdaptDTO implements Serializable {

    @ApiModelProperty(value = "业务单号",name = "bizCode")
    @NotBlank
    private String bizCode;

    @ApiModelProperty(value = "操作类型    REDUCE_FREEZING_STOCK-减少冻结库存（取消订单 减去冻结库存" +
            "INCREASE_FREEZING_STOCK-增加冻结库存（下单加冻结库存）",name = "operateType")
    @NotBlank
    private String operateType;

    @ApiModelProperty(value = "操作人名称",name = "operator")
    @NotBlank
    private String operator;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    @NotBlank
    private String operateCode;

    @ApiModelProperty(value = "商品信息",name = "skuStockList")
    private List<OperateFreezeStockSkuAdaptDTO> skuStockList;
}
