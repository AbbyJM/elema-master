package com.abby.elema;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Abby
 */
@ConfigurationProperties(prefix = "elema.instance-urls")
public class InstanceUrlsProperties {

    private String uaac;
    private String tdc;
    private String dmc;
    private String log;
    private String accessToken;
    private String userActive;

    public void setUaac(String uaac){
        this.uaac=uaac;
    }

    public String getUaac(){
        return uaac;
    }

    public void setTdc(String tdc){
        this.tdc=tdc;
    }

    public String getTdc(){
        return tdc;
    }

    public void setDmc(String dmc){
        this.dmc=dmc;
    }

    public String getDmc(){
        return dmc;
    }

    public void setLog(String log){
        this.log=log;
    }

    public String getLog(){
        return log;
    }

    public void setAccessToken(String token){
        accessToken=token;
    }

    public String getAccessToken(){
        return accessToken;
    }

    public void setUserActive(String userActive){
        this.userActive=userActive;
    }

    public String getUserActive(){
        return userActive;
    }
}
