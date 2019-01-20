package com.abby.elema.config;

import com.abby.elema.ElemaProperties;

import com.abby.elema.model.constants.RedisConstants;
import com.abby.elema.model.domain.UaacUserToken;
import com.abby.elema.model.dto.EnvironmentDto;
import com.abby.elema.model.dto.LoginUserDto;
import com.abby.elema.model.dto.UserAuthDto;
import com.abby.elema.model.enums.ResponseStatusEnum;


import com.abby.elema.service.UserService;
import com.abby.elema.service.UserTokenService;
import com.abby.elema.service.impl.UserTokenServiceImpl;

import com.abby.elema.util.LogUtil;
import com.abby.elema.util.TokenUtil;
import com.abby.elema.wrapper.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: Abby
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Resource
    private UserTokenService userTokenService;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String,Object> redisTemp;

    @Resource
    private ElemaProperties elemaProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        /*
        UaacUserToken userToken=userTokenService.getTokenOnlineByName(((UserDetails)principal).getUsername());
        if(userToken!=null){
            LogUtil.info("user has already logged in,skip it...");
            String message="you have already logged in";
            ResponseWrapper.error(ResponseStatusEnum.STATUS_RELOGIN.getCode(),message,httpServletResponse);
            return;
        }*/

        Object principal=authentication.getPrincipal();
        //extract the access token
        String token=TokenUtil.extractAccessToken(httpServletRequest);

        LogUtil.info("authentication successfully","login token is "+token);

        //build the login environment
        EnvironmentDto environmentDto=new EnvironmentDto();

        //get the user details
        UserAuthDto userAuthDto=userService.buildUserAuthDto(((UserDetails) principal).getUsername());

        try {
            //save the user token details in database
            userTokenService.saveUserToken(token,environmentDto,userAuthDto);
        } catch (Exception e) {
            LogUtil.info("the resources you requesting requires admin privilege," +
                    "failed to save token "+token,"the error message is "+e.getMessage());
        }

        LoginUserDto loginUserDto=new LoginUserDto();
        loginUserDto.setLoginTime(new Date().toString());
        loginUserDto.setUserId(userAuthDto.getUserId());
        loginUserDto.setUserName(userAuthDto.getUserName());
        loginUserDto.setUserRole(userAuthDto.getUserRole());

        //try to save the login user token to redis server
        redisTemp.opsForValue().set(token,loginUserDto,elemaProperties.getRedis().getLoginTokenValidationSeconds(),TimeUnit.SECONDS);

        httpServletResponse.setHeader("loginToken",token);
        httpServletResponse.setHeader("Access-Control-Expose-Headers","loginToken");
        ResponseWrapper.ok("login successfully",httpServletResponse);
    }
}
