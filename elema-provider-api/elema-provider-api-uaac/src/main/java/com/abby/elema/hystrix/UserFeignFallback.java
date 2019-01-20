package com.abby.elema.hystrix;

import com.abby.elema.interfaces.UserApi;
import com.abby.elema.model.dto.UserRegisterDto;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author: Abby
 */
public class UserFeignFallback implements UserApi {

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        return null;
    }

    @Override
    public String isUserSeller(String userName) {
        return "";
    }

    @Override
    public String isUserVerified(String userName) throws JsonProcessingException {
        return null;
    }

    @Override
    public void getUserBasicInfo() {

    }
}
