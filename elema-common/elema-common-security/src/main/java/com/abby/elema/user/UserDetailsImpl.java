package com.abby.elema.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author: Abby
 */
public class UserDetailsImpl implements UserDetails {

    private Collection<GrantedAuthority> userAuthorities=new ArrayList<>();
    private String userPassword;
    private String userName;
    private boolean isAccountNotExpired=false;
    private boolean isAccountNotLocked=false;
    private boolean isCredentialsNonExpired=false;
    private boolean isEnable=false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Collection<GrantedAuthority> authorities){
        userAuthorities=authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    public void setUserPassword(String password){
        userPassword=password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setUserName(String name){
        userName=name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNotExpired;
    }

    public void setAccountNotExpired(boolean expired){
        isAccountNotExpired=expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNotLocked;
    }

    public void setAccountNotLocked(boolean locked){
        isAccountNotLocked=locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean expired){
        isCredentialsNonExpired=expired;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    public void setEnable(boolean enable){
        isEnable=enable;
    }
}
