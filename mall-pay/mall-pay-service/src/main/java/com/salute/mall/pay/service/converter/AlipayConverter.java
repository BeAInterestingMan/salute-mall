package com.salute.mall.pay.service.converter;

import com.salute.mall.pay.api.request.OpenAlipayRequest;
import com.salute.mall.pay.service.pojo.dto.AlipayPayBaseDTO;
import com.salute.mall.pay.service.pojo.dto.MallPayBaseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlipayConverter {
    AlipayPayBaseDTO convertToAlipayPayBaseDTO(OpenAlipayRequest request);

    MallPayBaseDTO convertToMallPayBaseDTO(AlipayPayBaseDTO dto);
}
