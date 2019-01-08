package com.abby.elema.web.frontend;


import com.abby.elema.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Abby
 */
@RestController
public class TokenController {

    @Autowired
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
}
