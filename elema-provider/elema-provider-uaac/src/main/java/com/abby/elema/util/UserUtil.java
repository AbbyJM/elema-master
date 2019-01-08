package com.abby.elema.util;

/**
 * @author: Abby
 */
public class UserUtil {

    public static boolean checkUser(String username,String password,String email){
        if(username.length()<4||username.length()>20){
            return false;
        }
        if(password.length()<6||password.length()>20){
            return false;
        }

        return true;
    }
}
