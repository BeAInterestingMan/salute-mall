package com.salute.mall.order.service.service;

import com.salute.mall.order.service.pojo.dto.CreateSaleOrderDTO;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderResultDTO;

public interface SaleOrderService {
    /**
     * @Description 获取订单编号
     * @author liuhu
     * @date 2022/12/6 10:44
     * @return java.lang.String
     */
    String getSaleOrderCode();

    /**
     * @Description 创建订单
     * @author liuhu
     * @param dto
     * @date 2022/12/30 17:30
     * @return void
     */
    CreateSaleOrderResultDTO createSaleOrder(CreateSaleOrderDTO dto);
}
