package com.abby.elema.authenticate;

import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.util.LogUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Abby
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String token=httpServletRequest.getHeader("authorization");
        String userName=httpServletRequest.getParameter("username");
        LogUtil.info("Failed to login with login name "+userName,e.getMessage(),"the access token is "+token.substring(6));
        ResponseWrapper.error(ResponseStatusEnum.ACCESS_DENIED.getCode(),
                ResponseStatusEnum.ACCESS_DENIED.getMessage(),httpServletResponse);
    }
}