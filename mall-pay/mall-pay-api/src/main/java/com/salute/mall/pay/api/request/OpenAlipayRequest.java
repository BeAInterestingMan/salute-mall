package com.salute.mall.pay.api.request;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiOperation("支付宝支付入参")
public class OpenAlipayRequest implements Serializable {

    /**主单号*/
    private String mainOrderCode;

    /**子单号*/
    private String subOrderCode;

    /**系统来源*/
    private String systemSource;

    /**系统来源*/
    private String appId;

    /**系统来源*/
    private String payType;

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
