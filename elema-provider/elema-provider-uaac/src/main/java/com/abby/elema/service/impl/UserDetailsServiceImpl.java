package com.abby.elema.service.impl;

import com.abby.elema.exception.UserNotFoundException;
import com.abby.elema.mapper.UaacUserMapper;
import com.abby.elema.model.domain.UaacUser;

import com.abby.elema.user.UserDetailsImpl;
import com.abby.elema.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author: Abby
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UaacUserMapper uaacUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s){

        UaacUser user=uaacUserMapper.findUserByName(s);
        UserDetailsImpl userDetails=new UserDetailsImpl();
        userDetails.setEnable(true);
        userDetails.setUserName(s);
        userDetails.setUserPassword(user.getPassword());
        userDetails.setAccountNotExpired(true);
        userDetails.setAccountNotLocked(true);
        userDetails.setCredentialsNonExpired(true);
        return userDetails;
    }
}
