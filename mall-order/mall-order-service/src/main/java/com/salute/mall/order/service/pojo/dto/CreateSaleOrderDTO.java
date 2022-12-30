package com.salute.mall.order.service.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("创建订单入参")
public class CreateSaleOrderDTO implements Serializable {

    @ApiModelProperty(value = "订单编号",name = "saleOrderCode")
    @NotBlank
    private String saleOrderCode;

    @ApiModelProperty(value = "应付金额",name = "payableAmount")
    @NotNull
    @Max(10000)
    @Min(1)
    private BigDecimal payableAmount;

    @ApiModelProperty(value = "订单原始金额",name = "orderOriginAmount")
    @NotNull
    @Max(10000)
    @Min(0)
    private BigDecimal orderOriginAmount;

    @ApiModelProperty(value = "优惠券金额",name = "couponAmount")
    @NotNull
    @Max(10000)
    @Min(0)
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "优惠券优惠金额",name = "couponPreferentialAmount")
    @NotNull
    @Max(10000)
    @Min(0)
    private BigDecimal couponPreferentialAmount;

    @ApiModelProperty(value = "释放使用优惠券",name = "couponFlag")
    @NotNull
    private Boolean couponFlag;

    @ApiModelProperty(value = "优惠券编号",name = "couponCode")
    @NotBlank
    private String couponCode;

    @ApiModelProperty(value = "订单来源",name = "source")
    @NotBlank
    private String source;

    @ApiModelProperty(value = "订单备注",name = "orderRemark")
    private String orderRemark;

    @ApiModelProperty(value = "收件人",name = "customName")
    @NotBlank
    private String customName;

    @ApiModelProperty(value = "收件人编号",name = "customCode")
    @NotBlank
    private String customCode;

    @ApiModelProperty(value = "sku信息",name = "productSkuList")
    @NotEmpty
    private List<CreateSaleOrderProductSkuDTO> productSkuList;
}
