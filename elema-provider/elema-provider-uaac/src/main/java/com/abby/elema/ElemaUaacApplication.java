package com.abby.elema;

import com.abby.elema.util.LogUtil;
import com.mysql.cj.util.LogUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: Abby
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@RefreshScope
@EnableFeignClients
@EnableHystrix
@ServletComponentScan
public class ElemaUaacApplication {
    public static void main(String[] args){
        SpringApplication.run(ElemaUaacApplication.class,args);
    }
}
