package com.abby.elema.exception;

/**
 * @author: Abby
 */
public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(Throwable t){
        super(t);
    }

    public UserNotFoundException(String message,Throwable t){
        super(message,t);
    }
}
