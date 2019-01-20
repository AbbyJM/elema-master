package com.abby.elema.model.dto;

import java.io.Serializable;

/**
 * author: Abby
 */
public class UserBasicInfoDto implements Serializable {
    private String userName;
    private String mobilePhone;
    private String avatar;

    public void setUserName(String name){
        userName=name;
    }

    public String getUserName(){
        return userName;
    }

    public void setMobilePhone(String phone){
        mobilePhone=phone;
    }

    public String getMobilePhone(){
        return mobilePhone;
    }

    public void setAvatar(String avatar){
        this.avatar=avatar;
    }

    public String getAvatar(){
        return avatar;
    }
}
