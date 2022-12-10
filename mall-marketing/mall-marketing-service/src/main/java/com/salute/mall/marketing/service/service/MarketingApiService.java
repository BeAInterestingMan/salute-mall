package com.salute.mall.marketing.service.service;

import com.salute.mall.marketing.service.pojo.dto.PrepareOrderDTO;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderServiceDTO;
import com.salute.mall.marketing.service.pojo.dto.UseCouponServiceDTO;

public interface MarketingApiService {
     /**
      * @Description 使用优惠券
      * @author liuhu
      * @param dto
      * @date 2022/12/10 13:27
      * @return void
      */
     void useCoupon(UseCouponServiceDTO dto);

     /**
      * @Description 提交订单计算优惠
      * @author liuhu
      * @param dto
      * @date 2022/12/10 13:24
      * @return com.salute.mall.marketing.service.pojo.dto.PrepareOrderDTO
      */
     PrepareOrderDTO prepareOrder(PrepareOrderServiceDTO dto);
}
