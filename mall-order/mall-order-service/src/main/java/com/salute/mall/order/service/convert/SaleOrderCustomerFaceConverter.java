package com.salute.mall.order.service.convert;


import com.salute.mall.order.service.pojo.dto.CreateSaleOrderDTO;
import com.salute.mall.order.service.pojo.dto.CreateSaleOrderResultDTO;
import com.salute.mall.order.service.pojo.request.CreateSaleOrderRequest;
import com.salute.mall.order.service.pojo.response.CreateSaleOrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleOrderCustomerFaceConverter {
    CreateSaleOrderDTO convertToCreateSaleOrderDTO(CreateSaleOrderRequest request);

    CreateSaleOrderResponse convertToCreateSaleOrderResponse(CreateSaleOrderResultDTO saleOrderResultDTO);
}
