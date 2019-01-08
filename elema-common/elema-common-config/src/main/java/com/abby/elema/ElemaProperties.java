package com.abby.elema;

import com.abby.elema.redis.RedisProperties;
import com.abby.elema.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author: Abby
 */
@ConfigurationProperties(prefix = "elema")
public class ElemaProperties {

    private SecurityProperties security;

    private RedisProperties redis;

    public void setSecurity(SecurityProperties security){
        this.security=security;
    }

    public SecurityProperties getSecurity(){
        return security;
    }

    public RedisProperties getRedis(){
        return redis;
    }

    public void setRedis(RedisProperties redis){
        this.redis=redis;
    }
}
