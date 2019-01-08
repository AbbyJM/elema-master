package com.abby.elema.util;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import jdk.nashorn.internal.runtime.regexp.RegExpMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the regular expression util
 * @author: Abby
 */
public class RegExpUtil {

    private static Pattern pattern;
    private static Matcher matcher;


    /**
     * test if the string is an ip address
     * @param ip the ip string
     * @return true if it is ip address
     */
    public static boolean isIpAddress(String ip){
        String regExp="((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
        return check(ip,regExp);
    }


    private static boolean check(String str,String regExp){
        if(str==null||regExp==null){
            return false;
        }
        pattern=Pattern.compile(regExp);
        matcher=pattern.matcher(str);
        return matcher.matches();
    }
}
