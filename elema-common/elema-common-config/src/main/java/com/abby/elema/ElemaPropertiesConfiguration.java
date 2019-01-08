package com.abby.elema;

import com.abby.elema.mail.MailProperties;
import com.abby.elema.mq.RocketmqProperties;

import com.abby.elema.task.TaskExecutorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Abby
 */
@Configuration
@EnableConfigurationProperties({MailProperties.class,TaskExecutorProperties.class,
        ElemaProperties.class,RocketmqProperties.class,InstanceUrlsProperties.class})
public class ElemaPropertiesConfiguration {
}
