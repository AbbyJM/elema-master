package com.abby.elema.security;

/**
 * @author: Abby
 */
public class SecurityProperties {

    private Oauth2Properties oauth2;

    public void setOauth2(Oauth2Properties oauth2){
        this.oauth2=oauth2;
    }

    public Oauth2Properties getOauth2(){
        return oauth2;
    }
}
