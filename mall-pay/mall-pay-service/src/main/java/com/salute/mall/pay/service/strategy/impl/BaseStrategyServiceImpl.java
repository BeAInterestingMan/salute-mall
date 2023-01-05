package com.salute.mall.pay.service.strategy.impl;

import com.google.common.collect.Lists;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.pay.service.converter.PayStrategyConverter;
import com.salute.mall.pay.service.core.AlipayCore;
import com.salute.mall.pay.service.enums.PayStatusEnum;
import com.salute.mall.pay.service.pojo.dto.AlipayPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.MallPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.OpenPayCenterDTO;
import com.salute.mall.pay.service.pojo.entity.PayBill;
import com.salute.mall.pay.service.pojo.entity.PayChannelConfig;
import com.salute.mall.pay.service.repository.PayBillRepository;
import com.salute.mall.pay.service.repository.PayChannelConfigRepository;
import com.salute.mall.pay.service.strategy.PayStrategyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public  abstract class BaseStrategyServiceImpl implements PayStrategyService {

    @Autowired
    private PayBillRepository payBillRepository;

    @Autowired
    private PayChannelConfigRepository payChannelConfigRepository;


    @Override
    public OpenPayCenterDTO openPay(MallPayBaseDTO payBaseDTO) {
        //1.校验支付参数
        checkPayParamAvailable(payBaseDTO);
        //2.校验支付渠道是否合法
        checkPayChannel(payBaseDTO);
        //3.校验是否重复支付
        checkRepeatPay(payBaseDTO);
        //4.调用第三方唤起收银台
        OpenPayCenterDTO payCenterDTO = openPayCenter(payBaseDTO);
        //5.保存支付账单
        savePayBill(payBaseDTO);
        return payCenterDTO;
    }

    protected abstract void savePayBill(MallPayBaseDTO payBaseDTO);

    protected abstract void checkPayParamAvailable(MallPayBaseDTO payBaseDTO);

    protected  void doCheckPayParamAvailable(MallPayBaseDTO payBaseDTO){
        if(StringUtils.isAnyBlank(payBaseDTO.getMainOrderCode(),payBaseDTO.getSubOrderCode())){
            throw new BusinessException("500","主单号和子单号不能为空");
        }
        if(StringUtils.isAnyBlank(payBaseDTO.getSystemSource(),payBaseDTO.getPayScene())){
            throw new BusinessException("500","系统来源和支付场景不能为空");
        }
        if(StringUtils.isBlank(payBaseDTO.getSubject())){
            throw new BusinessException("500","支付主题不能为空");
        }
        if(payBaseDTO.getPayableAmount().compareTo(new BigDecimal(0) ) < 0){
            throw new BusinessException("500","支付金额不能小于0");
        }
    }

    private void checkPayChannel(MallPayBaseDTO payBaseDTO){
        PayChannelConfig payChannelConfig = payChannelConfigRepository.getBySystemSourceAndAppId(payBaseDTO.getSystemSource(), payBaseDTO.getAppId());
        SaluteAssertUtil.isTrue(Objects.nonNull(payChannelConfig),"当前系统未申请接入");
    }

    protected void checkRepeatPay(MallPayBaseDTO payBaseDTO){
       List<PayBill> payBills = payBillRepository.queryByMainCodeAndSubCode(payBaseDTO.getMainOrderCode(), payBaseDTO.getSubOrderCode());
       if(CollectionUtils.isEmpty(payBills)){
           return;
       }
       //存在支付成功的记录
       boolean match = payBills.stream().anyMatch(payBill -> Objects.equals(payBill.getPayStatus(), PayStatusEnum.PAY_SUCCESS.getValue()));
       SaluteAssertUtil.isTrue(match,"请勿重复发起支付");
    }

    protected abstract OpenPayCenterDTO openPayCenter(MallPayBaseDTO payBaseDTO);
}
