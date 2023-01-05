package com.salute.mall.pay.service.strategy;

import com.salute.mall.pay.service.pojo.dto.MallPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO;

public interface PayStrategyService {

    /**
     * @Description 打开收银台
     * @author liuhu
     * @param payBaseDTO
     * @date 2023/1/5 20:49
     * @return com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO
     */
    OpenPayCenterDTO openPay(MallPayBaseDTO payBaseDTO);
}
