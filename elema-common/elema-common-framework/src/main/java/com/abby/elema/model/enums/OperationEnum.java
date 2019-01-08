package com.abby.elema.model.enums;

/**
 * @author: Abby
 */
public enum  OperationEnum {

    UNKNOWN("UNKNOWN"),
    VALIDATOR_ERROR("VALIDATOR_ERROR"),
    TEST("TEST"),
    SEND_IMAGE_CODE("SEND_IMAGE_CODE"),
    SEND_EMAIL("SEND_EMAIL"),

    ACTIVE_USER("ACTIVE_USER"),
    DELETE_USER("DELETE_USER"),
    REGISTER_USER("REGISTER_USER");


    private String operateType;

    OperationEnum(String type){
        operateType=type;
    }

    public String getType(){
        return operateType;
    }
}
