package com.abby.elema.service;

import com.abby.elema.model.domain.UaacRole;
import com.abby.elema.model.domain.UaacUser;
import com.abby.elema.model.dto.*;

import java.util.List;

/**
 * @author: Abby
 */
public interface UserService {

    /**
     * find the user by id
     * @param id the user id
     * @return the user if exists or null if it does not
     */
    UaacUser findUserById(int id);

    /**
     * find the user by name
     * @param name the user name
     * @return the user if exists or null if it does not
     */
    UaacUser findUserByName(String name);

    /**
     * find the user by email
     * @param email the user email
     * @return the user if exists or null if it does not
     */
    UaacUser findUserByEmail(String email);

    /**
     * get users paged
     * @param pageNum the page number
     * @param pageSize the size of the page
     * @return list of the users
     */
    List<UaacUser> getUsersOfPage(int pageNum,int pageSize);

    /**
     * checkout if the user is online
     * @param name the user name
     * @return true if the user is online
     */
    boolean isUserOnline(String name);

    /**
     * build the user auth dto
     * @param username the user name
     * @return the user auth dto
     */
    UserAuthDto buildUserAuthDto(String username);

    /**
     * update the user
     * @param user the user object
     * @return true if updated successfully
     */
    boolean updateUser(UaacUser user);

    /**
     * register a user
     * @param registerDto the user dto
     * @return true if registered successfully
     */
    boolean registerUser(UserRegisterDto registerDto);

    /**
     * activate a user with status WAIT_ACTIVE
     * @param name the user name
     * @return true if we update the user successfully
     */
    boolean activateUser(String name);

    /**
     * lock the user
     * @param name the user name
     * @return true if successfully
     */
    boolean lockUser(String name);


    /**
     * get the roles of a user
     * @param name the name of the user
     * @return a list of the user roles
     */
    List<UaacRole> getUserRole(String name);


    boolean modifyPassword(ModifyPasswordDto modifyPasswordDto);

    /**
     * check if the user is seller
     * @param userName the user name
     * @return true if the user is seller
     */
    boolean isUserSeller(String userName);

    /**
     * check if real name verified
     * @param userName the user name
     */
    boolean isRealNameVerified(String userName);

    /**
     * get the user login dto form redis server
     * @param token the token
     * @return the login dto
     */
    LoginUserDto getLoginUserDto(String token);

    /**
     * get the basic user infomation
     * @param loginToken the login token of the user
     * @return the user basic info dto
     */
    UserBasicInfoDto getUserInfoBasic(String loginToken);

    /**
     * get the user avatar url
     * @param userName the user name
     * @return the user avatar url
     */
    String getUserAvatarUrl(String userName);

    /**
     * update the user avatar url
     * @param url the url
     * @return true if updated successfully
     */
    boolean updateAvatarUrl(String url,String userName);
}
