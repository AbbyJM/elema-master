package com.abby.elema.redis;


import com.abby.elema.model.constants.RedisConstants;
import com.abby.elema.service.UserTokenService;
import com.abby.elema.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;


/**
 * the redis message listener
 */
@Component
public class RedisMessageListener implements MessageListener {

    @Resource
    private UserTokenService tokenService;

    @Resource
    private RedisTemplate<String,Object> redisTemp;

    public RedisMessageListener(){

    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String msg=message.toString();
        if(msg.startsWith("eyJh")){
            LogUtil.info("the login token "+message.toString()+" has been expired");
            if(tokenService.setTokenOffline(message.toString())>0){
                LogUtil.info("updated user token status to offline successfully");
            }
        }else{
            LogUtil.info("the redis key "+msg+" has been expired ");
        }
    }
}
