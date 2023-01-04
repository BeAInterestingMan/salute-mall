package com.salute.mall.ability.service.converter;


import com.salute.mall.ability.service.pojo.dto.BusinessOperationLogDTO;
import com.salute.mall.ability.service.pojo.entity.BusinessOperationLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessOperationLogConverter {
    BusinessOperationLog convertToBusinessOperationLog(BusinessOperationLogDTO operationLogDTO);
}
