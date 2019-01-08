package com.abby.elema.mq;

import com.abby.elema.model.enums.TopicEnum;
import com.abby.elema.task.DefaultThreadPoolExecutor;
import org.apache.rocketmq.client.exception.MQClientException;

import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: Abby
 */
@Configuration
public class RocketMQProducerConfig {

    public static ConcurrentHashMap<String,MQProducer> PRODUCERS=new ConcurrentHashMap<>();

    @Resource
    private UaacTransactionalListener uaacTransactionalListener;

    @Autowired
    private RocketmqProperties rocketmqProperties;

    @Bean(destroyMethod = "shutdown",name = "uaacProducer")
    public TransactionMQProducer uaacProducer() throws MQClientException {
        TransactionMQProducer producer=new TransactionMQProducer();
        producer.setNamesrvAddr("localhost:9876");
        producer.setProducerGroup(TopicEnum.TOPIC_UAAC.getTopic());
        producer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        producer.setExecutorService(new DefaultThreadPoolExecutor());
        producer.setTransactionListener(uaacTransactionalListener);
        producer.start();
        PRODUCERS.put(TopicEnum.TOPIC_UAAC.getTopic(),producer);
        return producer;
    }

    public static MQProducer getProducer(String producerId){
        return  PRODUCERS.get(producerId);
    }
}
