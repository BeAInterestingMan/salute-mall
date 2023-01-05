package com.salute.mall.pay.service.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MallPayBaseDTO implements Serializable {

    private String payType;

    /**主单号*/
    private String mainOrderCode;

    /**子单号*/
    private String subOrderCode;

    /**appId*/
    private String appId;

    /**系统来源*/
    private String systemSource;

    /**支付场景*/
    private String payScene;

    /**支付宝 等第三方支付类型*/
    private String productCode;

    /**应付金额*/
    private BigDecimal payableAmount;

    private String remark;

    /**支付主题*/
    private String subject;
}
