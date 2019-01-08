package com.abby.elema.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Abby
 */
public class TokenUtil {

    /**
     *  extract the access token from the request
     * @param request the request
     * @return the access token
     */
    public static String extractAccessToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        String token="";
        if(header!=null&&header.startsWith("bearer")){
            token=header.substring(7);
        }else{
            LogUtil.info("failed to find token");
        }
        return token;
    }

    public static String extractLoginToken(HttpServletRequest request){
        String header=request.getHeader("loginToken");
        return header==null?"":header;
    }

    public static String getCurrentAccessToken(){
        HttpServletRequest request=RequestUtil.getRequest();
        return extractAccessToken(request);
    }
}
