package com.abby.elema.config;

import com.abby.elema.task.TaskExecutorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;


/**
 * @author: Abby
 */
@Configuration
@EnableAsync
public class AsyncTaskExecutorConfig implements AsyncConfigurer {

    @Resource
    private TaskExecutorProperties executorProperties;

    @Bean
    public TaskExecutor asyncExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(executorProperties.getCorePoolSize());
        executor.setKeepAliveSeconds(executorProperties.getKeepAliveSeconds());
        executor.setMaxPoolSize(executorProperties.getMaxPoolSize());
        executor.setQueueCapacity(executorProperties.getQueueCapacity());
        executor.setThreadNamePrefix(executorProperties.getThreadPrefix());
        return executor;
    }
}
