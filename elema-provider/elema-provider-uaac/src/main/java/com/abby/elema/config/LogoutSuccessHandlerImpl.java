package com.abby.elema.config;

import com.abby.elema.service.UserTokenService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.util.RequestUtil;
import com.abby.elema.util.TokenUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * default logout success handler
 * @author: Abby
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private RedisTemplate<String,Object> redisTemp;

    @Autowired
    private UserTokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LogUtil.info("successfully logged out ");
        //String accessToken=TokenUtil.extractAccessToken(request);
        String loginToken=TokenUtil.extractLoginToken(request);

        if(loginToken!=null&&redisTemp.delete(loginToken)){
            LogUtil.info("deleted LoginUserDto successfully for token "+loginToken);
        }
        if(loginToken!=null&&tokenService.setTokenOffline(loginToken)>0){
            LogUtil.info("updated token status to offline successfully");
            ResponseWrapper.ok("logged out successfully",response);
            return;
        }
    }
}
