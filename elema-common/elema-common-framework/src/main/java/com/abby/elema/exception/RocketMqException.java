package com.abby.elema.exception;

/**
 * @author: Abby
 */
public class RocketMqException extends Exception {

    public RocketMqException(String message){
        super(message);
    }

    public RocketMqException(Throwable t){
        super(t);
    }

    public RocketMqException(String message,Throwable t){
        super(message,t);
    }
}
