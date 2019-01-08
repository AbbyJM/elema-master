package com.abby.elema.mq;

import com.abby.elema.exception.RocketMqException;


import com.abby.elema.util.MessageUtil;


import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;

import org.springframework.stereotype.Component;

/**
 * @author: Abby
 */
@Component
public class RocketmqProducer {

    private TransactionMQProducer producer;

    private  final int RETRY_TIMES=3;

    public  TransactionSendResult sendTransactionMessage(String topic,String tag,String key,String body,String producerId) throws RocketMqException {
        int i=1;
        TransactionSendResult sendResult;
        Message message=MessageUtil.buildMessage(topic,tag,key,body);
        message.setDelayTimeLevel(0);
        producer= (TransactionMQProducer) RocketMQProducerConfig.getProducer(producerId);
        if(producer==null){
            throw new RocketMqException("failed to find producer with id "+producerId);
        }
        while (true){
            try {
                sendResult=producer.sendMessageInTransaction(message,null);
                break;
            } catch (Exception e) {
                if(i++>=RETRY_TIMES){
                    throw new RocketMqException("failed to send mq message "+e.getMessage());
                }
            }
        }
        return sendResult;
    }
}
