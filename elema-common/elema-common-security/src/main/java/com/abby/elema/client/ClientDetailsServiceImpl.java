package com.abby.elema.client;

import com.abby.elema.ElemaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * @author: Abby
 */
@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private ElemaProperties eleProperties;

    private ClientDetailsService clientDetailsService;

    @Autowired
    public ClientDetailsServiceImpl(ElemaProperties properties){
        eleProperties=properties;
        init();
    }

    public void init(){
        ClientDetailsServiceBuilder builder=new InMemoryClientDetailsServiceBuilder();
        try {
            builder
                    .withClient(eleProperties.getSecurity().getOauth2().getClientId())
                    .secret(eleProperties.getSecurity().getOauth2().getClientSecret())
                    .scopes("all")
                    .authorizedGrantTypes("password","client_credentials")
                    .accessTokenValiditySeconds(eleProperties.getSecurity().getOauth2().getAccessTokenValidationSeconds())
                    .and()
                    .withClient("feign")
                    .secret(eleProperties.getSecurity().getOauth2().getClientSecret())
                    .scopes("all")
                    .authorizedGrantTypes("password","client_credentials")
                    .accessTokenValiditySeconds(eleProperties.getSecurity().getOauth2().getAccessTokenValidationSeconds());

            clientDetailsService=builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }
}
