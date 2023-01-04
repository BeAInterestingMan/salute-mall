package com.salute.mall.ability.service.repository;

import com.salute.mall.ability.service.mapper.BusinessOperationLogMapper;
import com.salute.mall.ability.service.pojo.entity.BusinessOperationLog;
import com.salute.mall.common.core.utils.SaluteAssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class BusinessOperationLogRepository {

    @Autowired
    private BusinessOperationLogMapper businessOperationLogMapper;

    public void insert(BusinessOperationLog insert) {
        SaluteAssertUtil.isTrue(Objects.nonNull(insert),"参数异常");
        businessOperationLogMapper.insert(insert);
    }
}
