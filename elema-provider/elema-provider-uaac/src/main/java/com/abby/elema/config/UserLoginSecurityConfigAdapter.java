package com.abby.elema.config;

import com.abby.elema.security.ImageCodeFilter;
import com.abby.elema.security.UserAccountStatusFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author: Abby
 */
@Component
public class UserLoginSecurityConfigAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private UserAccountStatusFilter userAccountStatusFilter;

    @Override
    public void configure(HttpSecurity httpSecurity){
        httpSecurity.addFilterBefore(userAccountStatusFilter,ImageCodeFilter.class);
    }
}
