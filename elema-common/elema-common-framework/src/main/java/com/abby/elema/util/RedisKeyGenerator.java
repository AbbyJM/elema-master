package com.abby.elema.util;

import com.abby.elema.model.dto.UserAuthDto;

/**
 * @author: Abby
 */
public class RedisKeyGenerator {

    /**
     * the prefixes for building a redis key
     */
    public static final String UAAC_SAVE_TOKEN="uaac_save_user_token:";

    public static String generateLoginToken(String userName){
        return UAAC_SAVE_TOKEN+userName;
    }
}
