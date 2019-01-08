package com.abby.elema.security;

import com.abby.elema.authenticate.validate.ValidatorRepository;
import com.abby.elema.interfaces.IpAddressApi;
import com.abby.elema.interfaces.LogFeignApi;
import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.service.UserLogService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.util.RequestUtil;
import com.abby.elema.util.TokenUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Abby
 */
@Component
public class ImageCodeFilter extends OncePerRequestFilter {

    @Autowired
    private ValidatorRepository validatorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String device=request.getHeader("device");
        //we don't need image code filter on mobile devices
        if(device!=null&&(device.equals("Android")||device.equals("Iphone"))){
            filterChain.doFilter(request,response);
            return;
        }

        String validateText=validatorRepository.getValidateText(TokenUtil.extractAccessToken(request));
        String validator=request.getParameter("validator");
        if(validator==null||!validator.equals(validateText)){
            LogUtil.info("authentication failed,wrong validator");
            ResponseWrapper.error(ResponseStatusEnum.WRONG_VALIDATOR.getCode(),
                    ResponseStatusEnum.WRONG_VALIDATOR.getMessage(),response);
            return;
        }
        filterChain.doFilter(request,response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String uri=request.getRequestURI();
        return !uri.contains("/authenticate");
    }
}
