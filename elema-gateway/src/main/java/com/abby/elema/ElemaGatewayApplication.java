package com.abby.elema;

import com.abby.elema.mybatis.SqlSessionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author: Abby
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
//exclude the sql session config
@ComponentScan(excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,value = SqlSessionConfig.class))
public class ElemaGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(ElemaGatewayApplication.class,args);
    }
}
