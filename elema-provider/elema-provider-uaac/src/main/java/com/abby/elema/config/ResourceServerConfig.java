package com.abby.elema.config;

import com.abby.elema.authenticate.AuthenticationFailureHandlerImpl;
import com.abby.elema.model.constants.HttpConstants;
import com.abby.elema.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import org.springframework.security.web.authentication.RememberMeServices;

import javax.annotation.Resource;

/**
 * @author: Abby
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private ValidateCodeSecurityConfigAdapter validateCodeSecurityConfigAdapter;

    @Resource
    private UserLoginSecurityConfigAdapter userLoginSecurityConfigAdapter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .userDetailsService(userDetailsService)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .csrf().disable()
                .exceptionHandling()
                .and()
                .apply(validateCodeSecurityConfigAdapter)
                .and()
                .apply(userLoginSecurityConfigAdapter)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/api/user/active").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(HttpConstants.DEFAULT_LOGIN_PAGE)
                .loginProcessingUrl(HttpConstants.DEFAULT_LOGIN_PROCESS_URI)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout().logoutSuccessHandler(logoutSuccessHandler);
    }
}
