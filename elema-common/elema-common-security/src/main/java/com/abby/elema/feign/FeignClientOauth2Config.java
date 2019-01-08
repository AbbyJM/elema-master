package com.abby.elema.feign;

import com.abby.elema.ElemaProperties;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;


/**
 * @author: Abby
 */
@Configuration
public class FeignClientOauth2Config {

    @Autowired
    private ElemaProperties elemaProperties;

    @Bean
    public ClientCredentialsResourceDetails resourceDetails(){
        ClientCredentialsResourceDetails details=new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(elemaProperties.getSecurity().getOauth2().getAccessTokenUrl());
        details.setClientId("feign");
        details.setClientSecret("elema-secret");
        details.setAuthenticationScheme(AuthenticationScheme.valueOf(elemaProperties.getSecurity().getOauth2().getClientAuthenticationScheme()));
        return details;
    }

    @Bean("elemaOauth2RestTemplate")
    public OAuth2RestTemplate oAuth2RestTemplate(){
        OAuth2RestTemplate oAuth2RestTemplate=new OAuth2RestTemplate(resourceDetails(),new DefaultOAuth2ClientContext());
        oAuth2RestTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        return oAuth2RestTemplate;
    }


    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor feignTokenInterceptor(@Qualifier("elemaOauth2RestTemplate") OAuth2RestTemplate template){
        return new FeignTokenInterceptor(template);
    }

    @Bean
    public Request.Options options(){
        return new Request.Options(12000,12000);
    }
}
