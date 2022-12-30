package com.salute.mall.marketing.service.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  @Description 商品的优惠分摊
 *  @author liuhu
 *  @Date 2022/12/16 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDiscountPreferentialDTO implements Serializable {

    private String couponCode;

    private String couponName;

    private String skuCode;

    private Integer buyCount;

    private BigDecimal salePrice;

    private BigDecimal productOriginAmount;

    private BigDecimal productPreferentialAmount;
}
