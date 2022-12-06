package com.salute.mall.order.service.service;

import com.salute.mall.order.service.pojo.dto.CreateSaleOrderDTO;

public interface SaleOrderCustomerService {
    /**
     * @Description 获取订单编号
     * @author liuhu
     * @date 2022/12/6 10:44
     * @return java.lang.String
     */
    String getSaleOrderCode();

    void createSaleOrder(CreateSaleOrderDTO dto);
}
