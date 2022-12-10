package com.salute.mall.marketing.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableCouponInfoDTO implements Serializable {

    @ApiModelProperty(value = "优惠券编号",name = "couponCode")
    private String couponCode;

    @ApiModelProperty(value = "优惠券面值",name = "couponAmount")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "优惠券类型",name = "couponType")
    private String couponType;

    @ApiModelProperty(value = "优惠券门槛金额",name = "couponFullAmount")
    private BigDecimal couponFullAmount;

    @ApiModelProperty(value = "使用规则类型",name = "useType")
    private String useType;

    @ApiModelProperty(value = "满足的sku集合",name = "availableSkuList")
    private List<AvailableCouponDetailInfoDTO> availableSkuList;
}
