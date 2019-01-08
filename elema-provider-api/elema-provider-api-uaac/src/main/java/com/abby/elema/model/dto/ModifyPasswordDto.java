package com.abby.elema.model.dto;

import java.io.Serializable;

/**
 * @author: Abby
 */
public class ModifyPasswordDto implements Serializable {

    private String loginName;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public void setLoginName(String name){
        loginName=name;
    }

    public String getLoginName(){
        return loginName;
    }

    public void setOldPassword(String password){
        oldPassword=password;
    }

    public String getOldPassword(){
        return oldPassword;
    }

    public void setNewPassword(String password){
        newPassword=password;
    }

    public String getNewPassword(){
        return newPassword;
    }

    public void setConfirmPassword(String password){
        confirmPassword=password;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }
}
