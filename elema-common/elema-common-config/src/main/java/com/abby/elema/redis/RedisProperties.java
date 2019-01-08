package com.abby.elema.redis;

/**
 * @author: Abby
 */
public class RedisProperties {

    private int loginTokenValidationSeconds;

    public void setLoginTokenValidationSeconds(int seconds){
        loginTokenValidationSeconds=seconds;
    }

    public int getLoginTokenValidationSeconds(){
        return loginTokenValidationSeconds;
    }
}
