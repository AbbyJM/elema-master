package com.abby.elema.mq.consumer;

import com.abby.elema.model.enums.TopicEnum;
import com.abby.elema.mq.RocketmqProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author: Abby
 */
@Configuration
public class MessageConsumerConfig {

    @Resource
    private RocketmqProperties rocketmqProperties;

    @Resource
    private UaacMessageListener uaacMessageListener;

    @Bean(destroyMethod = "shutdown")
    public DefaultMQPushConsumer uaacMessageConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer(TopicEnum.TOPIC_UAAC.getTopic());
        consumer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        consumer.subscribe(TopicEnum.TOPIC_UAAC.getTopic(),"*");
        consumer.registerMessageListener(uaacMessageListener);
        consumer.setMaxReconsumeTimes(0);
        consumer.start();
        return consumer;
    }
}
