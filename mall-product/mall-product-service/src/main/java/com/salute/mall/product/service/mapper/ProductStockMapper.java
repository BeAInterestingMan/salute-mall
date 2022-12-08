package com.salute.mall.product.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDaoDTO;
import com.salute.mall.product.service.pojo.entity.ProductStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductStockMapper extends BaseMapper<ProductStock> {

    /**
     * @Description 批量操作库存
     * @author liuhu
     * @param dtoList
     * @date 2022/12/7 20:12
     * @return int
     */
    int batchOperateFreezeStock(@Param("itemList") List<OperateFreezeStockDaoDTO> dtoList);

    /**
     * @Description 操作冻结库存
     * @author liuhu
     * @param record
     * @date 2022/12/7 20:04
     * @return void
     */
    void doOperateFreezeStock(@Param("item")OperateFreezeStockDaoDTO record);
}
