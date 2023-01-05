package com.salute.mall.pay.service.service.impl;

import com.salute.mall.pay.service.converter.AlipayConverter;
import com.salute.mall.pay.service.core.MallPayCore;
import com.salute.mall.pay.service.pojo.dto.AlipayPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.MallPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO;
import com.salute.mall.pay.service.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private MallPayCore mallPayCore;

    @Autowired
    private AlipayConverter alipayConverter;

    @Override
    public OpenPayCenterDTO openAliPay(AlipayPayBaseDTO dto){
        MallPayBaseDTO centerDTO = alipayConverter.convertToMallPayBaseDTO(dto);
        return mallPayCore.openPay(centerDTO);
    }

}
