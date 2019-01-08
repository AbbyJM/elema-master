package com.abby.elema.mq;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Abby
 */
@ConfigurationProperties(prefix = "elema.rocketmq")
public class RocketmqProperties {

    private String namesrvAddr;
    private String defaultProducerGroup;

    public void setNamesrvAddr(String addr){
        namesrvAddr=addr;
    }

    public String getNamesrvAddr(){
        return namesrvAddr;
    }

    public void setDefaultProducerGroup(String group){
        defaultProducerGroup=group;
    }

    public String getDefaultProducerGroup(){
        return defaultProducerGroup;
    }
}
