package com.abby.elema.config;

import com.abby.elema.interfaces.LogFeignApi;
import com.abby.elema.mapper.UaacUserTokenMapper;
import com.abby.elema.model.domain.UaacUserToken;
import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.service.UserTokenService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.util.TokenUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Abby
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Resource
    private UaacUserTokenMapper tokenMapper;

    @Resource
    private UserTokenService tokenService;

    @Resource
    private RedisTemplate<String,Object> redisTemp;

    @Resource
    private LogFeignApi logFeignApi;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String accessToken=TokenUtil.extractLoginToken(request);

        //response to the client
        String message="authenticate failed,wrong credentials";
        ResponseWrapper.error(ResponseStatusEnum.WRONG_CREDENTIALS.getCode(),message,response);

        //if we found an access token,force it to offline
        if(accessToken!=null){
            tokenService.setTokenOffline(accessToken);
        }
    }
}