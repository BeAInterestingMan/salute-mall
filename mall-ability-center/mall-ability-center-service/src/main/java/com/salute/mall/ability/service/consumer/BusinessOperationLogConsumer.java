package com.salute.mall.ability.service.consumer;

import com.alibaba.fastjson.JSON;
import com.salute.mall.ability.service.converter.BusinessOperationLogConverter;
import com.salute.mall.ability.service.pojo.dto.BusinessOperationLogDTO;
import com.salute.mall.ability.service.pojo.entity.BusinessOperationLog;
import com.salute.mall.ability.service.repository.BusinessOperationLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 *  @Description 记录操作日志
 *  @author liuhu
 *  @Date 2023/1/4 13:54
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "businessOperationLogCreateConsumer",topic = "DELAY_ORDER")
public class BusinessOperationLogConsumer implements RocketMQListener<String> {

    @Autowired
    private BusinessOperationLogRepository businessOperationLogRepository;

    @Autowired
    private BusinessOperationLogConverter businessOperationLogConverter;

    @Override
    public void onMessage(String message) {
        log.info("execute BusinessOperationLogConsumer info,message:{}", message);
        if (StringUtils.isBlank(message)) {
            return;
        }
        BusinessOperationLogDTO operationLogDTO = JSON.parseObject(message, BusinessOperationLogDTO.class);
        if(Objects.isNull(operationLogDTO) || StringUtils.isAnyBlank(operationLogDTO.getAppId(),operationLogDTO.getLogDesc())){
            return;
        }
        BusinessOperationLog insert = buildInsertBusinessOperationLog(operationLogDTO);
        businessOperationLogRepository.insert(insert);
    }

    private BusinessOperationLog buildInsertBusinessOperationLog(BusinessOperationLogDTO operationLogDTO) {
        BusinessOperationLog businessOperationLog = businessOperationLogConverter.convertToBusinessOperationLog(operationLogDTO);
        businessOperationLog.setOperateTime(new Date());
        businessOperationLog.setCreator(operationLogDTO.getOperator());
        businessOperationLog.setCreatorCode(operationLogDTO.getOperateCode());
        businessOperationLog.setCreatedTime(new Date());
        return businessOperationLog;
    }
}
