package com.abby.elema.wrapper;

import java.io.Serializable;

/**
 * the response object for http requests,
 * simply wraps the status and message for logging
 * @author: Abby
 */
public class ResponseObject {

    private int status;

    private String message;

    private Object result;

    public void setStatus(int status){
        this.status=status;
    }

    public int getStatus(){
        return status;
    }

    public void setMessage(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

    public void setResult(Object result){
        this.result=result;
    }

    public Object getResult(){
        return result;
    }
}
