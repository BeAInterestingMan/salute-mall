package com.salute.mall.order.service.repository;

import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.order.service.mapper.SaleOrderMapper;
import com.salute.mall.order.service.pojo.entity.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SaleOrderRepository {

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    public void insert(SaleOrder saleOrder) {
        SaluteAssertUtil.isTrue(Objects.nonNull(saleOrder),"参数异常");
        saleOrderMapper.insert(saleOrder);
    }
}
