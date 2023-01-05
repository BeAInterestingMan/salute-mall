package com.salute.mall.pay.service.core;

import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.pay.service.factory.PayInstanceFactory;
import com.salute.mall.pay.service.pojo.dto.MallPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO;
import com.salute.mall.pay.service.strategy.PayStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class MallPayCore {

    @Autowired
    private PayInstanceFactory payInstanceFactory;
    /**
     * @Description 策略唤起收银台
     * @author liuhu
     * @param payBaseDTO
     * @date 2023/1/5 20:49
     * @return com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO
     */
    public OpenPayCenterDTO openPay(MallPayBaseDTO payBaseDTO){
        SaluteAssertUtil.isTrue(Objects.nonNull(payBaseDTO) && StringUtils.isNotBlank(payBaseDTO.getPayType()),"参数异常");
        PayStrategyService strategyService = payInstanceFactory.getPayStrategyService(payBaseDTO.getPayType());
        return strategyService.openPay(payBaseDTO);
    }
}
