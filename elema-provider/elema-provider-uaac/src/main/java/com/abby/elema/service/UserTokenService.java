package com.abby.elema.service;

import com.abby.elema.exception.UserNotFoundException;
import com.abby.elema.model.domain.UaacUserToken;
import com.abby.elema.model.dto.EnvironmentDto;
import com.abby.elema.model.dto.UserAuthDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Abby
 */
public interface UserTokenService {

    /**
     * save the user token to database
     * @param accessToken the access token
     * @param environmentDto the evironment dto
     * @param userAuthDto the user auth dto
     * @throws UserNotFoundException if the user cannot be found
     */
    void saveUserToken(String accessToken, EnvironmentDto environmentDto, UserAuthDto userAuthDto) throws UserNotFoundException;

    /**
     * set the access token to offline
     * @param accessToken the access token
     * @return true if updated the token status to offline successfully
     */
    int setTokenOffline(String accessToken);

    /**
     * get user tokens
     * @param accessToken the access token
     * @return a list of the user token
     */
    List<UaacUserToken> getUaacToken(String accessToken);

    /**
     * get the online token by user name
     * there can be only one online token of the user name
     * @param name the user name
     * @return the user token
     */
    UaacUserToken getTokenOnlineByName(String name);

    /**
     * check if a login token is valid
     * @param token the login token
     * @return true if the token is valid
     */
    boolean isLoginTokenValid(String token);

    boolean isLoginTokenValid(HttpServletRequest request);
}
