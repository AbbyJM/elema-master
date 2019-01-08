package com.abby.elema.model.enums;

/**
 * @author: Abby
 */
public enum  UserRoleEnum {

    ROLE_ANONYMOUS("ANONYMOUS"),
    ROLE_USER("USER"),
    ROLE_SELLER("SELLER"),
    ROLE_DEVELOPER("DEVELOPER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_SUPER_ADMIN("SUPER_ADMIN");

    /**
     * the user role enums
     */
    private String userRole;

    UserRoleEnum(String role){
        userRole=role;
    }

    public String getUserRole(){
        return userRole;
    }
}
