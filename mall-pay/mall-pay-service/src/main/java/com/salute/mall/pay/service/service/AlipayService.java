package com.salute.mall.pay.service.service;

import com.salute.mall.pay.service.pojo.dto.AlipayPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO;

public interface AlipayService {
    /**
     * @Description 支付宝支付
     * @author liuhu
     * @param dto
     * @date 2023/1/5 16:37
     * @return java.lang.String
     */
    OpenPayCenterDTO openAliPay(AlipayPayBaseDTO dto);
}
