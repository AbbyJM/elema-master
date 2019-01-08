package com.abby.elema.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Abby
 */
public class LoginUserDto implements Serializable {

    private static final long serialVersionUID = 209701798608250420L;

    private String userName;
    private int userId;
    private List<String> userRole;
    private String loginTime;

    public void setUserName(String name){
        userName=name;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserId(int id){
        userId=id;
    }

    public int getUserId(){
        return userId;
    }

    public void setLoginTime(String time){
        loginTime=time;
    }

    public String getLoginTime(){
        return loginTime;
    }

    public void setUserRole(List<String> role){
        userRole=role;
    }

    public List<String> getUserRole(){
        return userRole;
    }
}
