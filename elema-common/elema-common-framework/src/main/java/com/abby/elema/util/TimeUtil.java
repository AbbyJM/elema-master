package com.abby.elema.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Abby
 */
public class TimeUtil {

    public static String getCurrentTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String currentMD(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd");
        return dateFormat.format(new Date());
    }
}
