package com.salute.mall.product.service.service;

import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDTO;
import com.salute.mall.product.service.pojo.dto.stock.OperateRealStockDTO;

import java.util.List;

public interface ProductStockService {
    /**
     * @Description 操作冻结库存
     * @author liuhu
     * @param dto
     * @date 2022/12/6 16:47
     * @return void
     */
    void operateFreezeStock(OperateFreezeStockDTO dto);
    /**
     * @Description 操作真实库存
     * @author liuhu
     * @param dto
     * @date 2023/1/3 17:41
     * @return void
     */
    void operateRealStock(OperateRealStockDTO dto);
}
