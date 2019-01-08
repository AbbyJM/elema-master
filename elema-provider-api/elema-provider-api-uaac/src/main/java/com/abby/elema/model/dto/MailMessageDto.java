package com.abby.elema.model.dto;

import java.io.Serializable;

/**
 * @author: Abby
 */
public class MailMessageDto implements Serializable {

    private String from;
    private String to;
    private String subject;
    private String text;
    private String userName;

    public void setFrom(String from){
        this.from=from;
    }

    public String getFrom(){
        return from;
    }

    public void setTo(String to){
        this.to=to;
    }

    public String getTo(){
        return to;
    }

    public void setSubject(String subject){
        this.subject=subject;
    }

    public String getSubject(){
        return subject;
    }

    public void setText(String text){
        this.text=text;
    }

    public String getText(){
        return text;
    }

    public void setUserName(String name){
        userName=name;
    }

    public String getUserName(){
        return userName;
    }
}
