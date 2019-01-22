package com.abby.elema.security;

import com.abby.elema.model.domain.UaacUser;
import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.model.enums.UserAccountStatus;
import com.abby.elema.service.UserService;
import com.abby.elema.service.UserTokenService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Abby
 */
@Component
public class UserAccountStatusFilter extends OncePerRequestFilter {

    @Resource
    private UserService userService;

    @Resource
    private UserTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(tokenService.isLoginTokenValid(request)){
            LogUtil.info("the user has already logged in,skip it...");
            String message="you have already logged in";
            ResponseWrapper.error(ResponseStatusEnum.STATUS_RELOGIN.getCode(),message,response);
            return;
        }

        //check if the user is locked
        String userName=request.getParameter("username");
        if(userName==null){
            ResponseWrapper.error(500,"please provider parameter user name on your request");
        }

        UaacUser user=userService.findUserByName(userName);
        if(user==null){
            LogUtil.info("the user with name "+userName+" cannot be found");
            return;
        }
        //retrieve the user status
        String userStatus=user.getUserStatus();

        //do not allow the user login if the user status if not normal
        if(userStatus.equals(UserAccountStatus.NORMAL.getStatus())){
            filterChain.doFilter(request,response);
        }
        else if(userStatus.equals(UserAccountStatus.WAIT_ACTIVE.getStatus())){
            ResponseWrapper.error(ResponseStatusEnum.ACCOUNT_WAIT_ACTIVE.getCode(),ResponseStatusEnum.ACCOUNT_WAIT_ACTIVE.getMessage(),response);
            return;
        }
        else if(userStatus.equals(UserAccountStatus.LOCKED.getStatus())){
            ResponseWrapper.error(ResponseStatusEnum.ACCOUNT_LOCKED.getCode(),ResponseStatusEnum.ACCOUNT_LOCKED.getMessage(),response);
            return;
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String uri=request.getRequestURI();
        return !uri.contains("/authenticate");
    }
}
