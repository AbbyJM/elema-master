package com.abby.elema.filter;

import com.abby.elema.exception.AuthHeaderException;
import com.abby.elema.util.LogUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * the header filter
 * pass the auth header to the micro service
 * @author: Abby
 */
@Component
public class HeaderFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        String uri=request.getRequestURI();
        return !uri.contains("/oauth");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        LogUtil.info("requesting for authorization...");
        try{
            HttpServletRequest request=requestContext.getRequest();
            String header=request.getHeader("authorization");

            if(header==null||!header.startsWith("bearer")){
                throw new AuthHeaderException("failed to find access token in header,access denied");
            }

            LogUtil.info("found access token in header ",header);

            //we need to pass the auth header to the micro services
            requestContext.addZuulRequestHeader("authorization",header);
        }catch (Exception e){
            LogUtil.info("failed to handle header in gateway","the error message is "+e.getMessage());
        }
        return null;
    }
}
