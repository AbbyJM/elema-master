package com.abby.elema.web.frontend;


import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.service.UserTokenService;
import com.abby.elema.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Abby
 */
@RestController
public class TokenController {

    @Resource
    private UserTokenService tokenService;

    @PostMapping(value = "/admin/token/forceOffline")
    public boolean forceTokenOffline(@RequestParam("accessToken") String accessToken){
        return tokenService.setTokenOffline(accessToken)>0;
    }

    @RequestMapping(value = "/api/token/login/isValid",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void isLoginTokenValid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isValid=tokenService.isLoginTokenValid(request);
        if(isValid){
            ResponseWrapper.ok("the login token is valid",response);
        }else{
            ResponseWrapper.error(ResponseStatusEnum.LOGIN_TOKEN_INVALID.getCode(),ResponseStatusEnum.LOGIN_TOKEN_INVALID.getMessage(),response);
        }
    }
}
