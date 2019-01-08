package com.abby.elema.exception;

/**
 * @author: Abby
 */
public class AuthHeaderException extends Exception{

    public AuthHeaderException(String s){
        super(s);
    }

    public AuthHeaderException(Throwable t){
        super(t);
    }

    public AuthHeaderException(String s,Throwable t){
        super(s,t);
    }
}
