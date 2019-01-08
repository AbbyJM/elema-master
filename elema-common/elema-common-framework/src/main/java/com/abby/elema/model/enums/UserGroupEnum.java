package com.abby.elema.model.enums;

/**
 * @author: Abby
 */
public enum  UserGroupEnum {

    /**
     * the user group enums
     */

    UNKNOWN("UNKNOWN"),
    MANAGEMENT("MANAGEMENT"),
    FINANCE("FINANCE"),
    DEVELOPMENT("DEVELOPMENT");

    private String userGroup;

    UserGroupEnum(String group){
        userGroup=group;
    }

    public String getUserGroup(){
        return userGroup;
    }
}
