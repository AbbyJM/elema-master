package com.abby.elema.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Abby
 */
public class UserAuthDto implements Serializable {

    private static final long serialVersionUID = 2296218361081746494L;

    private int userId;
    private String userName;
    private List<String> userRole;
    private String userStatus;

    public void setUserId(int id){
        userId=id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserName(String name){
        userName=name;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserRole(List<String> role){
        userRole=role;
    }

    public List<String> getUserRole(){
        return userRole;
    }

    public void setUserStatus(String status){
        userStatus=status;
    }

    public String getUserStatus(){
        return userStatus;
    }

}