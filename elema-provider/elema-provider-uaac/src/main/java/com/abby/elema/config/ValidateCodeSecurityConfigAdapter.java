package com.abby.elema.config;

import com.abby.elema.security.ImageCodeFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: Abby
 */
@Component
public class ValidateCodeSecurityConfigAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private ImageCodeFilter filter;

    @Override
    public void configure(HttpSecurity httpSecurity){
        httpSecurity.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
    }
}
