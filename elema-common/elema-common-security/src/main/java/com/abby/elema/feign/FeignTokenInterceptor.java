package com.abby.elema.feign;

import com.abby.elema.model.constants.HttpConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * @author: Abby
 */
public class FeignTokenInterceptor implements RequestInterceptor {

    private OAuth2RestTemplate oAuth2RestTemplate;

    public FeignTokenInterceptor(OAuth2RestTemplate template){
        oAuth2RestTemplate=template;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token="bearer "+oAuth2RestTemplate.getAccessToken().toString();
        requestTemplate.header(HttpConstants.HEADER_AUTHORIZATION,token);
    }
}
