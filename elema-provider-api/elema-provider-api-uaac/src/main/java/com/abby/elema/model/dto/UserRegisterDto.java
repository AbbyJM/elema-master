package com.abby.elema.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Abby
 */
public class UserRegisterDto implements Serializable {

    private static final long serialVersionUID = 6906414408192581408L;

    private String name;

    private String password;

    private String email;

    private List<String> roles;

    private String group;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public List<String> getRoles(){
        return roles;
    }

    public void setRoles(List<String> roles){
        this.roles=roles;
    }

    public String getGroup(){
        return group;
    }

    public void setGroup(String group){
        this.group=group;
    }
}
