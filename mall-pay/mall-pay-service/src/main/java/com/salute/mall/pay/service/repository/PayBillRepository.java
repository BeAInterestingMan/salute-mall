package com.salute.mall.pay.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.pay.service.enums.PayStatusEnum;
import com.salute.mall.pay.service.mapper.PayBillMapper;
import com.salute.mall.pay.service.pojo.entity.PayBill;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class PayBillRepository {

    @Autowired
    private PayBillMapper payBillMapper;

    public List<PayBill> queryByMainCodeAndSubCode(String mainOrderCode, String subOrderCode) {
        SaluteAssertUtil.isTrue(!StringUtils.isAnyBlank(mainOrderCode,subOrderCode),"参数异常");
        LambdaQueryWrapper<PayBill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayBill::getMainOrderCode,mainOrderCode)
                    .eq(PayBill::getSubOrderCode,subOrderCode);
        return payBillMapper.selectList(queryWrapper);
    }

    public void insert(PayBill payBill) {
        SaluteAssertUtil.isTrue(Objects.nonNull(payBill),"参数异常");
        payBillMapper.insert(payBill);
    }
}
