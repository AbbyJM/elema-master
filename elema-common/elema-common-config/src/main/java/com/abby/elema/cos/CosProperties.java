package com.abby.elema.cos;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: Abby
 */
@ConfigurationProperties(prefix = "elema.cos")
public class CosProperties {

    private String secretId;
    private String secretKey;
    private String bucket;
    private String region;

    public void setSecretId(String id){
        secretId=id;
    }

    public String getSecretId(){
        return secretId;
    }

    public void setSecretKey(String key){
        secretKey=key;
    }

    public String getSecretKey(){
        return secretKey;
    }

    public void setBucket(String bucket){
        this.bucket=bucket;
    }

    public String getBucket(){
        return bucket;
    }

    public void setRegion(String region){
        this.region=region;
    }

    public String getRegion(){
        return region;
    }
}
