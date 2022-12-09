package com.salute.mall.marketing.service.service;

import com.salute.mall.marketing.service.pojo.dto.PrepareOrderServiceDTO;
import com.salute.mall.marketing.service.pojo.dto.UseCouponServiceDTO;

public interface MarketingApiService {
     void useCoupon(UseCouponServiceDTO dto);

     void prepareOrder(PrepareOrderServiceDTO dto);
}
