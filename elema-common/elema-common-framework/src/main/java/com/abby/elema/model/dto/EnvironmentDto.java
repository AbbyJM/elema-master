package com.abby.elema.model.dto;

import java.io.Serializable;

/**
 * @author: Abby
 */
public class EnvironmentDto implements Serializable {

    private static final long serialVersionUID = 5223577571980851583L;
    private String targetOs;
    private String ipAddress;
    private String browserType;
    private String locationName;

    public void setTargetOs(String os){
        targetOs=os;
    }

    public String getTargetOs(){
        return targetOs;
    }

    public void setIpAddress(String ip){
        ipAddress=ip;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public void setBrowserType(String browser){
        browserType=browser;
    }

    public String getBrowserType(){
        return browserType;
    }

    public void setLocationName(String location){
        locationName=location;
    }

    public String getLocationName(){
        return locationName;
    }
}

