package com.abby.elema.model.enums;

/**
 * @author: Abby
 */
public enum  StoreStatusEnum {

    CHECKING("CHECKING"),
    NORMAL("NORMAL"),
    FORCE_CLOSE("FORCE_CLOSE");

    private String status;

    StoreStatusEnum(String status){
        this.status=status;
    }

    public String getStatus(){
        return status;
    }
}
