package com.abby.elema.service.impl;

import com.abby.elema.exception.UserNotFoundException;
import com.abby.elema.mapper.UaacUserMapper;
import com.abby.elema.mapper.UaacUserTokenMapper;

import com.abby.elema.model.domain.UaacUserToken;
import com.abby.elema.model.dto.EnvironmentDto;


import com.abby.elema.model.dto.LoginUserDto;
import com.abby.elema.model.dto.UserAuthDto;
import com.abby.elema.service.UserTokenService;

import com.abby.elema.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author: Abby
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserTokenServiceImpl implements UserTokenService {

    @Resource
    private UaacUserTokenMapper tokenMapper;

    @Resource
    private UaacUserMapper userMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemp;

    @Override
    public void saveUserToken(String accessToken, EnvironmentDto environmentDto, UserAuthDto userAuthDto) throws UserNotFoundException {
        UaacUserToken token=new UaacUserToken();

        token.setUserId(userAuthDto.getUserId());
        token.setUserName(userAuthDto.getUserName());
        token.setAccessToken(accessToken);
        token.setLoginTime(new Date());
        token.setTargetOs(environmentDto.getTargetOs());
        token.setTargetBrowser(environmentDto.getBrowserType());
        token.setTargetIp(environmentDto.getIpAddress());
        token.setLoginLocation(environmentDto.getLocationName());
        token.setIsOnline(true);
        token.setUserStatus(userAuthDto.getUserStatus());
        tokenMapper.insert(token);
    }

    @Override
    public int setTokenOffline(String accessToken) {
        if(redisTemp.hasKey(accessToken)&&redisTemp.delete(accessToken)){
            LogUtil.info("removed login token "+accessToken+" on redis server");
        }
        UaacUserToken token=tokenMapper.getOnlineByAccessToken(accessToken);
        if(token==null){
            return 0;
        }
        token.setIsOnline(false);
        return tokenMapper.updateByPrimaryKeySelective(token);
    }

    @Override
    public List<UaacUserToken> getUaacToken(String accessToken) {
        if(accessToken==null||accessToken.isEmpty()){
            return null;
        }
        return tokenMapper.getByAccessToken(accessToken);
    }

    @Override
    public UaacUserToken getTokenOnlineByName(String name) {
        if(name.isEmpty()){
            return null;
        }
        return tokenMapper.getOnlineByName(name);
    }

    @Override
    public boolean isLoginTokenValid(String token) {
        return redisTemp.opsForValue().get(token)!=null;
    }

    @Override
    public boolean isLoginTokenValid(HttpServletRequest request) {
        String token=request.getHeader("loginToken");
        LogUtil.info("token is "+token);
        if(token==null){
            return false;
        }
        return redisTemp.hasKey(token);
    }

    @Override
    public int loginTokenSize() {
        Set<String> keys=redisTemp.keys("eyJh"+"*");
        if(keys==null||keys.isEmpty()){
            return 0;
        }
        return keys.size();
    }

    @Override
    public String getUserNameOfLoginToken(String loginToken) {
        if(loginToken==null||loginToken.isEmpty()){
            return null;
        }
        LoginUserDto loginUserDto=(LoginUserDto) redisTemp.opsForValue().get(loginToken);
        return loginUserDto==null?null:loginUserDto.getUserName();
    }
}
