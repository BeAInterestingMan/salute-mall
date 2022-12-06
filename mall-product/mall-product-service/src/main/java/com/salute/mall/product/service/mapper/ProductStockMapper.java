package com.salute.mall.product.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salute.mall.product.service.pojo.dto.stock.OperateFreezeStockDaoDTO;
import com.salute.mall.product.service.pojo.entity.ProductStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductStockMapper extends BaseMapper<ProductStock> {

    int batchOperateFreezeStock(@Param("itemList") List<OperateFreezeStockDaoDTO> dtoList);

    void doOperateFreezeStock(@Param("item")OperateFreezeStockDaoDTO record);
}
