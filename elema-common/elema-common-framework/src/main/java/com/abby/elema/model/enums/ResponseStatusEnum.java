package com.abby.elema.model.enums;

/**
 * @author: Abby
 */
public enum  ResponseStatusEnum {

    STATUS_SUCCESS(200,"success"),

    //authentication status
    ACCESS_DENIED(10000101,"access denied"),
    WRONG_VALIDATOR(10000102,"wrong validator"),
    TOKEN_NOT_FOUND(10000103,"failed to find token"),
    WRONG_CREDENTIALS(10000104,"wrong credentials"),
    ACCOUNT_LOCKED(10000105,"the account was locked"),
    ACCOUNT_WAIT_ACTIVE(10000106,"the account needs to be actived"),

    //user online status
    STATUS_ONLINE(20000101,"online"),
    STATUS_OFFLINE(20000102,"offline"),
    STATUS_RELOGIN(20000103,"relogin"),

    //user operation status
    REGISTER_FAILED(40000101,"register failed"),
    ILLEGAL_PARAMS(40000102,"illege user params"),
    NEED_SUPER_ADMIN(40000103,"the operation needs super admin privilege"),

    MQ_SEND_ERROR(50000101,"mq send error"),
    MQ_TRANSACTION_ERROR(50000102,"mq transaction error");
    private String message;
    private int code;

    ResponseStatusEnum(int code,String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

    public int getCode(){
        return code;
    }

    public static String getMessage(int code){
        ResponseStatusEnum[] responseStatus=ResponseStatusEnum.values();
        for(ResponseStatusEnum status:responseStatus){
            if(status.getCode()==code){
                return status.getMessage();
            }
        }
        return null;
    }
}
