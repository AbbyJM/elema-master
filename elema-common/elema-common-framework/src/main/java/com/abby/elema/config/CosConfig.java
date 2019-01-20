package com.abby.elema.config;

import com.abby.elema.cos.CosProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

/**
 * author: Abby
 */
@Configuration
public class CosConfig {

    @Resource
    private CosProperties cosProperties;

    @Bean(destroyMethod = "shutdown")
    @Lazy
    public COSClient cosClient(){
        COSCredentials cosCre=new BasicCOSCredentials(cosProperties.getSecretId(),cosProperties.getSecretKey());
        ClientConfig config=new ClientConfig(new Region(cosProperties.getRegion()));
        return new COSClient(cosCre,config);
    }
}
