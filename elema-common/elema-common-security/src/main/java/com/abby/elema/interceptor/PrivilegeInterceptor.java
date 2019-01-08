package com.abby.elema.interceptor;

import com.abby.elema.annotation.NeedSuperAdmin;
import com.abby.elema.model.constants.UrlPrefixConstants;
import com.abby.elema.model.dto.LoginUserDto;
import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.model.enums.UserRoleEnum;
import com.abby.elema.util.LogUtil;
import com.abby.elema.util.TokenUtil;

import com.abby.elema.wrapper.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

/**
 * the global privilege interceptor
 * @author: Abby
 */
@Component
public class PrivilegeInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,Object> redisTemp;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl=request.getRequestURI();
        if(requestUrl.contains(UrlPrefixConstants.PUBLIC)){
            LogUtil.info("request for public resources,let it pass");
            return true;
        }
        if(requestUrl.contains(UrlPrefixConstants.OAUTH)){
            LogUtil.info("request for authorization,let it pass");
            return true;
        }
        if(requestUrl.contains(UrlPrefixConstants.TEST)){
            LogUtil.info("request for test,let it pass");
            return true;
        }
        if(requestUrl.contains(UrlPrefixConstants.ERROR)){
            LogUtil.info("request for error page,let it pass");
            return true;
        }
        if(requestUrl.contains(UrlPrefixConstants.API)){
            LogUtil.info("the open api");
            return true;
        }
        //check if the user is logged in
        String loginToken=TokenUtil.extractLoginToken(request);
        LoginUserDto loginUserDto=(LoginUserDto)redisTemp.opsForValue().get(loginToken);
        if(loginUserDto!=null){
            if(needSuperAdminPrivilege(handler)){
                List<String> roles=loginUserDto.getUserRole();
                if(roles.contains(UserRoleEnum.ROLE_SUPER_ADMIN.getUserRole())){
                    LogUtil.info("found super admin,grant all privilege");
                    return true;
                }else{
                    ResponseWrapper.error(ResponseStatusEnum.ACCESS_DENIED.getCode(),
                            ResponseStatusEnum.ACCESS_DENIED.getMessage(),response);
                    return false;
                }
            }
            LogUtil.info("found logged in user "+loginUserDto.getUserName()+",grant privilege");
        }else if(request.getUserPrincipal()!=null&&request.getUserPrincipal().getName().equals("feign")){
            //check if the request is made by feign client by checking the authenticated name
            LogUtil.info("the feign request,let it pass");
        }
        else{
            LogUtil.info("failed to find user login token in request");
            ResponseWrapper.error(ResponseStatusEnum.TOKEN_NOT_FOUND.getCode(),
                    ResponseStatusEnum.TOKEN_NOT_FOUND.getMessage(),response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

    /**
     * find out if the request was annotated with @NeedSuperAdmin
     * so that it needs the super admin privilege
     * @param handler the handler
     * @return true if need a super admin privilege
     */
    private boolean needSuperAdminPrivilege(Object handler){
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        NeedSuperAdmin needSuperAdmin=AnnotationUtils.findAnnotation(method,NeedSuperAdmin.class);
        return needSuperAdmin!=null;
    }
}
