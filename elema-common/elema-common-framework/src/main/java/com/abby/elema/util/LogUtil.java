package com.abby.elema.util;

import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * the log util
 * @author: Abby
 */
public class LogUtil {

    private static Map<String,Logger> loggers=new HashMap<>();

    private static void initLogger(String className){
        Logger logger=Logger.getLogger(className);
        loggers.put(className,logger);
    }

    private static Logger getLogger(String className){
        Logger logger=loggers.get(className);
        if(logger==null){
            initLogger(className);
            logger=loggers.get(className);
        }
        return logger;
    }

    public static void info(String... values){

        //get the caller's name
        String className=Thread.currentThread().getStackTrace()[2].getClassName();
        Logger logger=getLogger(className);

        if(values.length==1){
            for(String value:values){
                logger.info(value);
            }
            return;
        }

        int maxLength=0;
        for(String value:values){
            if(value.length()>maxLength){
                maxLength=value.length();
            }
        }

        StringBuilder logBorderBuilder= new StringBuilder("***************INFO***************");
        while(maxLength>logBorderBuilder.length()){
            logBorderBuilder.append("*");
        }

        String logBorder=logBorderBuilder.toString();
        logger.info(logBorder);

        for(String value:values){
            logger.info(value);
        }
        logger.info(logBorder+"\n");
    }


    public static void error(String... values){
        String className=Thread.currentThread().getStackTrace()[2].getClassName();
        Logger logger=getLogger(className);
        for(String value:values){
            logger.error(value);
        }
    }

    public static void warn(String... values){
        String className=Thread.currentThread().getStackTrace()[2].getClassName();
        Logger logger=getLogger(className);
        for(String value:values){
            logger.warn(value);
        }
    }

    public static void debug(String... values){
        String className=Thread.currentThread().getStackTrace()[2].getClassName();
        Logger logger=getLogger(className);
        for(String value:values){
            logger.debug(value);
        }
    }
}

