package com.abby.elema;


import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;



/**
 * @author: Abby
 */
@EnableEurekaServer
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ElemaEurekaServerApplication {
    Logger logger= Logger.getLogger(ElemaEurekaServerApplication.class);
    public static void main(String[] args){
        SpringApplication.run(ElemaEurekaServerApplication.class,args);
    }


}
