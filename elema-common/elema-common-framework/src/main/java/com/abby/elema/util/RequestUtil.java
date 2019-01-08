package com.abby.elema.util;

import com.abby.elema.model.constants.HttpConstants;
import com.abby.elema.model.dto.EnvironmentDto;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abby
 */
public class RequestUtil {

    /**
     * gets the current request
     * @return the request
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes attrs=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs != null ? attrs.getRequest() : null;
    }

    /**
     * gets the ip address of the request
     * @param request the request
     * @return the ip address
     */
    public static String getIpAddress(HttpServletRequest request){
        String ip=request.getHeader(HttpConstants.X_REAL_IP);
        if(StringUtils.isEmpty(ip)||HttpConstants.UNKNOWN.equalsIgnoreCase(ip)){
            ip=request.getHeader(HttpConstants.X_FORWARDED_FOR);
        }
        if(StringUtils.isEmpty(ip)||HttpConstants.UNKNOWN.equalsIgnoreCase(ip)){
            ip=request.getHeader(HttpConstants.PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HttpConstants.WL_PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HttpConstants.HTTP_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HttpConstants.HTTP_X_FORWARDED_FOR);
        }
        if (StringUtils.isBlank(ip) || HttpConstants.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * gets all headers info in a request
     * @param request the request
     * @return the headers
     */
    public static Map<String,String> getHeadersInfo(HttpServletRequest request){
        Map<String,String> result=new HashMap<>(20);
        Enumeration headerNames=request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key.trim(),value);
        }
        return result;
    }

    public static String getOs(HttpServletRequest request){
        UserAgent userAgent=UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent.getOperatingSystem().getName();
    }

    public static String getBrowser(HttpServletRequest request){
        UserAgent userAgent=UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent.getBrowser().getName();
    }

}
