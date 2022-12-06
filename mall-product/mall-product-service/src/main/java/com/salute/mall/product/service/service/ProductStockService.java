package com.salute.mall.product.service.service;

import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;

import java.util.List;

public interface ProductStockService {
    /**
     * @Description 操作冻结库存
     * @author liuhu
     * @param dtoList
     * @date 2022/12/6 16:47
     * @return void
     */
    void operateFreezeStock(List<OperateFreezeStockDTO> dtoList);
}
