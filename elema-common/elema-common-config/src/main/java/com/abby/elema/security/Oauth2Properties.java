package com.abby.elema.security;

/**
 * @author: Abby
 */
public class Oauth2Properties {

    private String tokenStore;

    private int accessTokenValidationSeconds;

    private String jwtSignKey;

    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    private String authUrl;

    private String clientAuthenticationScheme;

    public String getClientId(){
        return clientId;
    }

    public void setClientId(String id){
        clientId=id;
    }

    public String getClientSecret(){
        return clientSecret;
    }

    public void setClientSecret(String secret){
        clientSecret=secret;
    }

    public void setTokenStore(String token){
        tokenStore=token;
    }

    public String getTokenStore(){
        return tokenStore;
    }

    public void setAccessTokenValidationSeconds(int seconds){
        accessTokenValidationSeconds=seconds;
    }

    public int getAccessTokenValidationSeconds(){
        return accessTokenValidationSeconds;
    }

    public void setJwtSignKey(String key){
        jwtSignKey=key;
    }

    public String getJwtSignKey(){
        return jwtSignKey;
    }

    public String getAccessTokenUrl(){
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String url){
        accessTokenUrl=url;
    }

    public String getAuthUrl(){
        return authUrl;
    }

    public void setAuthUrl(String url){
        authUrl=url;
    }

    public void setClientAuthenticationScheme(String scheme){
        clientAuthenticationScheme=scheme;
    }

    public String getClientAuthenticationScheme(){
        return clientAuthenticationScheme;
    }
}
