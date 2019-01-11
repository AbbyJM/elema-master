package com.abby.elema.service.impl;

import com.abby.elema.mapper.UaacRoleMapper;
import com.abby.elema.mapper.UaacUserMapper;
import com.abby.elema.model.domain.UaacRole;
import com.abby.elema.model.domain.UaacUser;

import com.abby.elema.model.domain.UaacUserToken;
import com.abby.elema.model.dto.LoginUserDto;
import com.abby.elema.model.dto.ModifyPasswordDto;
import com.abby.elema.model.dto.UserAuthDto;

import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.UserAccountStatus;
import com.abby.elema.model.enums.UserRoleEnum;
import com.abby.elema.service.UserService;
import com.abby.elema.service.UserTokenService;

import com.abby.elema.util.LogUtil;
import com.github.pagehelper.PageHelper;
import com.google.inject.internal.util.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Abby
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UaacUserMapper uaacUserMapper;

    @Resource
    private UaacRoleMapper uaacRoleMapper;

    @Resource
    private UserTokenService tokenService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private EmailServiceImpl emailService;

    @Autowired
    private RedisTemplate<String,Object> redisTemp;

    @Override
    public UaacUser findUserById(int id) {
        UaacUser user=uaacUserMapper.selectByPrimaryKey(id);
        if(user!=null){
            user.setPassword("");
        }
        return user;
    }

    @Override
    public UaacUser findUserByName(String name) {
        if(name==null||name.isEmpty()){
            return null;
        }
        UaacUser user=uaacUserMapper.findUserByName(name);
        if(user!=null){
            user.setPassword("");
        }
        return user;
    }

    @Override
    public UaacUser findUserByEmail(String email) {
        if(email==null||email.isEmpty()){
            return null;
        }
        UaacUser user=uaacUserMapper.findUserByEmail(email);
        if(user!=null){
            user.setPassword("");
        }
        return user;
    }

    @Override
    public List<UaacUser> getUsersOfPage(int pageNum, int pageSize) {
        if(pageNum>0&&pageSize>0){
            PageHelper.startPage(pageNum,pageSize);
            return uaacUserMapper.getAllUsers();
        }
        return null;
    }

    @Override
    public boolean isUserOnline(String name) {
        UaacUserToken token=tokenService.getTokenOnlineByName(name);
        return token!=null;
    }

    @Override
    public UserAuthDto buildUserAuthDto(String username) {
        UaacUser user=uaacUserMapper.findUserByName(username);
        UserAuthDto userAuthDto=new UserAuthDto();

        if(user!=null){
            userAuthDto.setUserId(user.getId());
            userAuthDto.setUserName(user.getUsername());
            userAuthDto.setUserStatus(user.getUserStatus());
            List<String> roles=new ArrayList<>();
            List<UaacRole> userRoles=uaacRoleMapper.getRolesOfUserName(username);
            for(UaacRole role:userRoles){
                roles.add(role.getUserRole());
            }
            userAuthDto.setUserRole(roles);
        }
        return userAuthDto;
    }

    @Override
    public boolean updateUser(UaacUser user) {
        if(user==null){
            return false;
        }
        return uaacUserMapper.updateByPrimaryKey(user)>0;
    }

    @Override
    public boolean registerUser(UserRegisterDto registerDto) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(registerDto.getName()),"user name cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(registerDto.getPassword()),"password cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(registerDto.getPassword()),"email cannot be null");

        UaacUser user=new UaacUser();
        user.setUsername(registerDto.getName());

        //need to active the user
        user.setUserStatus(UserAccountStatus.WAIT_ACTIVE.getStatus());
        String encodedPassword=passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(registerDto.getEmail());
        user.setUserGroup(registerDto.getGroup());
        user.setCreatedTime(new Date());
        return uaacUserMapper.insert(user)>0;
    }

    @Override
    public boolean activateUser(String name) {
        UaacUser user=uaacUserMapper.findUserByName(name);
        if(user==null){
            LogUtil.info("user with name "+name+" cannot be found ");
            return false;
        }
        user.setUserStatus(UserAccountStatus.NORMAL.getStatus());
        return uaacUserMapper.updateByPrimaryKey(user)>0;
    }

    @Override
    public boolean lockUser(String name) {
        UaacUser uaacUser=uaacUserMapper.findUserByName(name);
        if(uaacUser!=null){
            uaacUser.setUserStatus(UserAccountStatus.LOCKED.getStatus());
        }
        return uaacUserMapper.updateByPrimaryKey(uaacUser)>0;
    }

    @Override
    public List<UaacRole> getUserRole(String name) {
        return uaacRoleMapper.getRolesOfUserName(name);
    }

    @Override
    public boolean modifyPassword(ModifyPasswordDto modifyPasswordDto) {
        if(!checkModifyPasswordDto(modifyPasswordDto)){
            return false;
        }
        UaacUser user=uaacUserMapper.findUserByName(modifyPasswordDto.getLoginName());
        if(user==null){
            LogUtil.info("user with name "+modifyPasswordDto.getLoginName()+" not found");
            return false;
        }

        String encodePassword=passwordEncoder.encode(modifyPasswordDto.getNewPassword());
        if(encodePassword.equals(modifyPasswordDto.getOldPassword())){
           LogUtil.info("the user "+modifyPasswordDto.getLoginName()+"'s new password is equal with the old");
           return false;
        }

        user.setPassword(encodePassword);
        user.setUpdateTime(new Date());
        return uaacUserMapper.updateByPrimaryKey(user)>0;
    }

    @Override
    public boolean isUserSeller(String userName) {
        List<UaacRole> roles=uaacRoleMapper.getRolesOfUserName(userName);
        for(UaacRole user:roles){
            if(user.getUserRole().equals("SELLER")){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isRealNameVerified(String userName) {
        //TODO
        return true;
    }

    @Override
    public LoginUserDto getLoginUserDto(String token) {
        return (LoginUserDto) redisTemp.opsForValue().get(token);
    }

    private boolean checkModifyPasswordDto(ModifyPasswordDto modifyPasswordDto){
        Preconditions.checkArgument(StringUtils.isNotEmpty(modifyPasswordDto.getLoginName()),"user name cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(modifyPasswordDto.getOldPassword()),"old password cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(modifyPasswordDto.getNewPassword()),"new password cannot be null");
        if(!modifyPasswordDto.getNewPassword().equals(modifyPasswordDto.getConfirmPassword())){
            return false;
        }
        return true;
    }
}
