package com.abby.elema.feign;

import com.abby.elema.exception.RocketMqException;
import com.abby.elema.interfaces.MQProducerApi;
import com.abby.elema.model.dto.RocketMessageDto;
import com.abby.elema.model.enums.ResponseStatusEnum;

import com.abby.elema.mq.RocketmqProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;


/**
 * @author: Abby
 */
@RestController
public class MQProducerFeign implements MQProducerApi {

    @Autowired
    private RocketmqProducer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String sendTransactionMessage(@RequestBody RocketMessageDto rocketMessageDto) throws JsonProcessingException, RocketMqException {
        String topic=rocketMessageDto.getTopic();
        String tag=rocketMessageDto.getTag();
        String body=rocketMessageDto.getBody();
        String key=rocketMessageDto.getKey();
        String producerId=rocketMessageDto.getProducerId();
        TransactionSendResult sendResult=producer.sendTransactionMessage(topic,tag,key,body,producerId);
        int status;
        String message;
        String sendStatus=sendResult.getSendStatus().toString();
        String transactionState=sendResult.getLocalTransactionState().toString();

        if(sendStatus.equals("SEND_OK")&&transactionState.equals("COMMIT_MESSAGE")){
           status=ResponseStatusEnum.STATUS_SUCCESS.getCode();
           message=ResponseStatusEnum.STATUS_SUCCESS.getMessage();
        }else if(!sendResult.equals("SEND_OK")){
            status=ResponseStatusEnum.MQ_SEND_ERROR.getCode();
            message=ResponseStatusEnum.MQ_SEND_ERROR.getMessage();
        }else{
            status=ResponseStatusEnum.MQ_TRANSACTION_ERROR.getCode();
            message=ResponseStatusEnum.MQ_TRANSACTION_ERROR.getMessage();
        }

        Map<String,Object> result=new HashMap<>(3);
        result.put("status",status);
        result.put("transactionState",transactionState);
        result.put("message",message);
        return objectMapper.writeValueAsString(result);
    }
}
