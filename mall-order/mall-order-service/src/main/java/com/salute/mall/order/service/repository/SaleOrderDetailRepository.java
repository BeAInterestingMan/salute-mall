package com.salute.mall.order.service.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import com.salute.mall.common.datasource.helper.MybatisBatchHelper;
import com.salute.mall.order.service.mapper.SaleOrderDetailMapper;
import com.salute.mall.order.service.pojo.entity.SaleOrderDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleOrderDetailRepository {

    @Autowired
    private SaleOrderDetailMapper saleOrderDetailMapper;

    @Autowired
    private MybatisBatchHelper mybatisBatchHelper;

    /**
     * @Description 批量新增
     * @author liuhu
     * @param saleOrderDetails
     * @date 2022/12/7 20:22
     * @return void
     */
    public void insertBatch(List<SaleOrderDetail> saleOrderDetails) {
        SaluteAssertUtil.isTrue(CollectionUtils.isNotEmpty(saleOrderDetails),"参数异常");
        mybatisBatchHelper.batchInsertOrUpdate(saleOrderDetails,SaleOrderDetailMapper.class,(record,mapper)->mapper.insert(record));
    }

    public List<SaleOrderDetail> queryBySaleOrderCode(String saleOrderCode) {
        if(StringUtils.isBlank(saleOrderCode)){
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<SaleOrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SaleOrderDetail::getSaleOrderCode,saleOrderCode);
        return saleOrderDetailMapper.selectList(queryWrapper);
    }
}
