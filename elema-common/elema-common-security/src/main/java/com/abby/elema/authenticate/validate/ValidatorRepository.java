package com.abby.elema.authenticate.validate;

import com.abby.elema.authenticate.validate.code.ValidateCode;
import com.abby.elema.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: Abby
 */
@Component
public class ValidatorRepository {

    @Autowired
    private RedisTemplate<String,Object> redisTemp;

    public void saveValidator(ValidateCode code){
        String accessToken=TokenUtil.getCurrentAccessToken();
        redisTemp.opsForValue().set(buildKey(accessToken),code.getValidateText(),code.getExpiredTime(),TimeUnit.SECONDS);
    }

    public String getValidateText(String accessToken){
        String text="";
        text=(String)redisTemp.opsForValue().get(buildKey(accessToken));
        return text;
    }

    private String buildKey(String token){
        return "validator:"+token;
    }
}
