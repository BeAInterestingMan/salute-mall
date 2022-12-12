package com.salute.mall.marketing.service.service;

import com.salute.mall.marketing.service.pojo.dto.*;

import java.util.List;

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
    /**
     * @Description 获取商品维度可参与优惠券活动
     * @author liuhu
     * @param dto
     * @date 2022/12/12 13:59
     * @return com.salute.mall.marketing.service.pojo.dto.ProductCouponInfoDTO
     */
    List<ProductCouponInfoDTO> queryProductCouponInfo(ProductCouponInfoServiceDTO dto);

    /**
     * @Description 领券
     * @author liuhu
     * @param dto
     * @date 2022/12/12 14:31
     * @return void
     */
    void receiveCoupon(ReceiveCouponDTO dto);
}
