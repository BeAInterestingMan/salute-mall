package com.salute.mall.product.service.pojo.dto.stock;

import com.salute.mall.product.api.request.OperateFreezeStockSkuRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
public class OperateRealStockDTO implements Serializable {

    @ApiModelProperty(value = "业务单号",name = "bizCode")
    @NotBlank
    private String bizCode;

    @ApiModelProperty(value = "操作类型 REDUCE_REAL_STOCK-扣除真实库存（发货-扣真实库存-减少冻结库存） " +
            "INCREASE_REAL_STOCK-增加真实库存（退货收货-增加真实库存）",name = "operateType")
    @NotBlank
    private String operateType;

    @ApiModelProperty(value = "操作人名称",name = "operator")
    @NotBlank
    private String operator;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    @NotBlank
    private String operateCode;

    @ApiModelProperty(value = "商品信息",name = "skuStockList")
    private List<OperateFreezeStockSkuRequest> skuStockList;
}
