package com.salute.mall.marketing.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface MarketingCouponStockMapper extends BaseMapper<MarketingCouponStock> {

    /**
     * @Description 更新数量
     * @author liuhu
     * @param sendQty
     * @param couponActivityCode
     * @date 2022/12/12 15:32
     * @return int
     */
    int updateCouponNum(@Param("sendQty") int sendQty,@Param("couponActivityCode") String couponActivityCode);
}
