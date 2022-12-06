package com.salute.mall.product.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class OperateFreezeStockRequest implements Serializable {

    @ApiModelProperty(value = "业务单号",name = "bizCode")
    @NotBlank
    private String bizCode;


    @ApiModelProperty(value = "操作人名称",name = "operator")
    @NotBlank
    private String operator;

    @ApiModelProperty(value = "操作人编号",name = "operateCode")
    @NotBlank
    private String operateCode;
}
