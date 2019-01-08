package com.abby.elema.model.enums;

/**
 * @author: Abby
 */
public enum  TransactionStateEnum {

    COMMIT("COMMIT"),
    ROLLBACK("ROLLBACK"),
    UNKNOWN("UNKNOWN");
    private String state;

    TransactionStateEnum(String state){
        this.state=state;
    }

    public String getState(){
        return state;
    }
}
