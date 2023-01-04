package com.salute.mall.order.service.helper;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSendHelper {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void sendSync(String topic,String tag, String message){
        log.info("execute sendSync info,topic:{},tag:{},message:{}",topic,tag,message);
        if(StringUtils.isAnyBlank(topic,message)){
            return;
        }
        String destination=StringUtils.isBlank(tag)?tag:topic+":"+tag;
        rocketMQTemplate.asyncSend(destination, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("execute sendSync success,destination:{},message:{},result:{}",destination,message, JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("execute sendSync error,destination:{},message:{}",destination,message,throwable);
            }
        });
    }


    public void sendDelayMessage(String topic,String tag, String message,long timeout, int delayLevel){
        log.info("execute sendSync info,topic:{},tag:{},message:{}",topic,tag,message);
        if(StringUtils.isAnyBlank(topic,message)){
            return;
        }
        String destination=StringUtils.isBlank(tag)?tag:topic+":"+tag;
        Message<?> messageObj = MessageBuilder.withPayload(message).build();
        rocketMQTemplate.asyncSend(destination, messageObj, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("execute sendSync success,destination:{},message:{},result:{}",destination,message, JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("execute sendSync error,destination:{},message:{}",destination,message,throwable);
            }
        },timeout,delayLevel);
    }
}
