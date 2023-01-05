package com.salute.mall.pay.service.strategy.impl;

import cn.hutool.core.util.IdUtil;
import com.salute.mall.pay.service.converter.PayStrategyConverter;
import com.salute.mall.pay.service.core.AlipayCore;
import com.salute.mall.pay.service.enums.PayStatusEnum;
import com.salute.mall.pay.service.pojo.dto.AlipayPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.MallPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO;
import com.salute.mall.pay.service.pojo.entity.PayBill;
import com.salute.mall.pay.service.repository.PayBillRepository;
import com.salute.mall.pay.service.strategy.PayStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service(value = "alipayStrategyService")
public class AlipayStrategyServiceImpl extends BaseStrategyServiceImpl implements PayStrategyService {

    @Autowired
    private PayStrategyConverter payStrategyConverter;

    @Autowired
    private PayBillRepository payBillRepository;

    @Autowired
    private AlipayCore alipayCore;

    @Override
    protected void savePayBill(MallPayBaseDTO payBaseDTO) {
        PayBill payBill = new PayBill();
        payBill.setPayBillCode(IdUtil.getSnowflake().nextIdStr());
        payBill.setMainOrderCode(payBaseDTO.getMainOrderCode());
        payBill.setSubOrderCode(payBaseDTO.getSubOrderCode());
        payBill.setPayType(payBaseDTO.getPayType());
        payBill.setPayTime(new Date());
        payBill.setPaySence(payBaseDTO.getPayScene());
        payBill.setPayableAmount(payBaseDTO.getPayableAmount());
        payBill.setPayStatus(PayStatusEnum.WAIT_PAY.getValue());
        payBillRepository.insert(payBill);
    }

    @Override
    protected void checkPayParamAvailable(MallPayBaseDTO payBaseDTO) {
          super.doCheckPayParamAvailable(payBaseDTO);
    }

    @Override
    protected OpenPayCenterDTO openPayCenter(MallPayBaseDTO payBaseDTO) {
        AlipayPayBaseDTO alipayPayBaseDTO =  payStrategyConverter.convertToAlipayPayBaseDTO(payBaseDTO);
        String payUrl = alipayCore.openAliPay(alipayPayBaseDTO);
        return OpenPayCenterDTO.builder().payUrl(payUrl).build();
    }
}
