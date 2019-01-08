package com.abby.elema.model.enums;

import org.springframework.security.core.parameters.P;

/**
 * @author: Abby
 */
public enum  UserAccountStatus {

    NORMAL(30000101,"NORMAL"),
    LOCKED(30000102,"LOCKED"),
    WAIT_ACTIVE(30000103,"WAIT_ACTIVE");

    private String status;
    private int code;

    UserAccountStatus(int code,String status){
        this.status=status;
        this.code=code;
    }

    public String getStatus(){
        return status;
    }
}
