package com.abby.elema.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisListenerConfig {

    @Autowired
    private RedisConnectionFactory factory;

    @Autowired
    private RedisMessageListener listener;

    @Bean
    public RedisMessageListenerContainer listenerContainer(){
        RedisMessageListenerContainer container=new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        //listen for the expiration event
        //make sure that notify-keyspace-events Ex was set in redis.conf
        container.addMessageListener(listener,new ChannelTopic( "__keyevent@0__:expired" ));
        return container;
    }
}