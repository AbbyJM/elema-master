package com.abby.elema.task;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Abby
 */
@ConfigurationProperties(prefix = "elema.executor")
public class TaskExecutorProperties {

    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private int keepAliveSeconds;
    private String threadPrefix;

    public void setCorePoolSize(int poolSize){
        corePoolSize=poolSize;
    }

    public int getCorePoolSize(){
        return corePoolSize;
    }

    public void setMaxPoolSize(int max){
        maxPoolSize=max;
    }

    public int getMaxPoolSize(){
        return maxPoolSize;
    }

    public void setQueueCapacity(int capacity){
        queueCapacity=capacity;
    }

    public int getQueueCapacity(){
        return queueCapacity;
    }

    public void setKeepAliveSeconds(int seconds){
        keepAliveSeconds=seconds;
    }

    public int getKeepAliveSeconds(){
        return keepAliveSeconds;
    }

    public void setThreadPrefix(String name){
        threadPrefix=name;
    }

    public String getThreadPrefix(){
        return threadPrefix;
    }
}
