package com.salute.mall.order.service.repository;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salute.mall.common.core.exception.BusinessException;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.order.service.mapper.SaleOrderMapper;
import com.salute.mall.order.service.pojo.entity.SaleOrder;
import com.salute.mall.order.service.pojo.po.CancelOrderPO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
public class SaleOrderRepository {

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    public void insert(SaleOrder saleOrder) {
        SaluteAssertUtil.isTrue(Objects.nonNull(saleOrder),"参数异常");
        saleOrderMapper.insert(saleOrder);
    }

    public SaleOrder getBySaleOrderCode(String saleOrderCode) {
        if(StringUtils.isBlank(saleOrderCode)){
            return null;
        }
        LambdaQueryWrapper<SaleOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SaleOrder::getSaleOrderCode,saleOrderCode);
        return saleOrderMapper.selectOne(queryWrapper);
    }

    public void cancelOrder(CancelOrderPO orderPO) {
        SaluteAssertUtil.isTrue(Objects.nonNull(orderPO) && !StringUtils.isAnyBlank(orderPO.getSaleOrderCode()),"参数异常");
        LambdaQueryWrapper<SaleOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SaleOrder::getSaleOrderCode,orderPO.getSaleOrderCode());
        SaleOrder update = new SaleOrder();
        update.setCancelType(orderPO.getCancelType());
        update.setCancelReason(orderPO.getReason());
        update.setModifiedTime(new Date());
        update.setModifier(orderPO.getOperator());
        update.setModifierCode(orderPO.getOperateCode());
        int rows = saleOrderMapper.update(update, queryWrapper);
        if(rows !=1){
          log.error("取消订单更新数据异常,rows:{}，req:{}",rows, JSON.toJSONString(orderPO));
          throw new BusinessException("500","取消订单更新数据异常");
        }
    }
}
