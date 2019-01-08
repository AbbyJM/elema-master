package com.abby.elema.model.dto;

import java.io.Serializable;

/**
 * @author: Abby
 */
public class ResetPasswordDto implements Serializable {

    private String userName;
    private String email;

    public void setUserName(String name){
        userName=name;
    }

    public String getUserName(){
        return userName;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }
}
