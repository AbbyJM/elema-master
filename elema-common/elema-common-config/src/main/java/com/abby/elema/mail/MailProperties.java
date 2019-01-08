package com.abby.elema.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Abby
 */
@ConfigurationProperties(prefix = "elema.mail")
public class MailProperties {

    private String host;
    private String username;
    private String password;
    private int port;
    private String protocol;
    private String defaultEncoding;

    public void setHost(String host){
        this.host=host;
    }

    public String getHost(){
        return host;
    }

    public void setUsername(String name){
        username=name;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return password;
    }

    public void setPort(int port){
        this.port=port;
    }

    public int getPort(){
        return port;
    }

    public void setProtocol(String protocol){
        this.protocol=protocol;
    }

    public String getProtocol(){
        return protocol;
    }

    public void setDefaultEncoding(String encoding){
        defaultEncoding=encoding;
    }

    public String getDefaultEncoding(){
        return defaultEncoding;
    }
}
