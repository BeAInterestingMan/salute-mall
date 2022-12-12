package com.salute.mall.marketing.service.converter;

import com.salute.mall.marketing.service.pojo.context.ProductContext;
import com.salute.mall.marketing.service.pojo.dto.AvailableCouponDiscountInfoDTO;
import com.salute.mall.marketing.service.pojo.dto.PrepareOrderDTO;
import com.salute.mall.marketing.service.pojo.dto.ProductCouponInfoDTO;
import com.salute.mall.marketing.service.pojo.dto.ProductCouponInfoServiceDTO;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponActivity;
import com.salute.mall.marketing.service.pojo.entity.MarketingCouponUserRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarketingApiServiceConverter {

    PrepareOrderDTO convertToPrepareOrderDTO(AvailableCouponDiscountInfoDTO infoDTO);

    ProductContext convertToProductContext(ProductCouponInfoServiceDTO dto);

    List<ProductCouponInfoDTO> convertToProductCouponInfoDTOList(List<MarketingCouponActivity> couponActivityList);

    MarketingCouponUserRecord convertToMarketingCouponUserRecord(MarketingCouponActivity couponActivity);
}
