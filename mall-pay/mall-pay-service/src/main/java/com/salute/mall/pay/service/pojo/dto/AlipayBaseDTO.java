package com.salute.mall.pay.service.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlipayBaseDTO implements Serializable {

    /**订单金额*/
    private String out_trade_no;

    /**单号*/
    private String total_amount;

    /**订单标题*/
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 产品编号，支付方式不同，传的数据不同
     * 如果是PC网页支付，这个是必传参数 "FAST_INSTANT_TRADE_PAY";
     * 如果是扫码支付，这个是选传参数"FACE_TO_FACE_PAYMENT";
     */
    private String product_code;
}
