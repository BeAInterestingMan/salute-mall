package com.salute.mall.pay.service.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AlipayPayBaseDTO implements Serializable {

    /**主单号*/
    private String mainOrderCode;

    /**子单号*/
    private String subOrderCode;

    /**系统来源*/
    private String systemSource;

    private String payType;

    private String appId;

    /**支付场景*/
    private String payScene;

    private String productCode;

    /**校验编号*/
    private String tradeCode;

    /**应付金额*/
    private BigDecimal payableAmount;

    private String remark;

    /**支付主题*/
    private String subject;
}
