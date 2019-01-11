package com.abby.elema.web.frontend;


import com.abby.elema.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Abby
 */
@RestController
public class TokenController {

    @Resource
    private UserTokenService tokenService;

    /**
     * force the token to offline
     * @param accessToken the access token
     * @return true if update the access token status successfully
     */
    @PostMapping(value = "/admin/token/forceOffline")
    public boolean forceTokenOffline(@RequestParam("accessToken") String accessToken){
        return tokenService.setTokenOffline(accessToken)>0;
    }

    @GetMapping(value = "/api/token/login/isValid",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean isLoginTokenValid(HttpServletRequest request, HttpServletResponse response){
        return false;
    }
}
