package com.abby.elema.web.frontend;

import com.abby.elema.annotation.NeedSuperAdmin;
import com.abby.elema.annotation.SaveLog;
import com.abby.elema.exception.RocketMqException;
import com.abby.elema.interfaces.MQProducerApi;
import com.abby.elema.model.constants.HttpConstants;
import com.abby.elema.model.dto.RocketMessageDto;
import com.abby.elema.model.dto.UserBasicInfoDto;
import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.*;

import com.abby.elema.service.UserService;
import com.abby.elema.service.UserTokenService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.util.UserUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;


import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: Abby
 */
@RestController
public class UserController {

    @Resource
    private MQProducerApi producerApi;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemp;

    @Resource
    private UserService userService;

    @Resource
    private UserTokenService tokenService;

    @RequestMapping(value = "/api/user/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SaveLog(type = OperationEnum.REGISTER_USER)
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, HttpServletResponse response) throws IOException, RocketMqException {
        if(!UserUtil.checkUser(username,password,email)){
            ResponseWrapper.error(ResponseStatusEnum.ILLEGAL_PARAMS.getCode(),
                    ResponseStatusEnum.ILLEGAL_PARAMS.getMessage(),response);
        }
        UserRegisterDto userRegisterDto=new UserRegisterDto();
        userRegisterDto.setName(username);
        userRegisterDto.setPassword(password);
        userRegisterDto.setEmail(email);

        //the register user role default to be USER
        List<String> role=new ArrayList<>();
        role.add(UserRoleEnum.ROLE_USER.getUserRole());
        userRegisterDto.setRoles(role);

        //by default the user does not belong to any group
        userRegisterDto.setGroup(null);

        RocketMessageDto rocketMessageDto=new RocketMessageDto();
        rocketMessageDto.setProducerId(TopicEnum.TOPIC_UAAC.getTopic());
        rocketMessageDto.setKey(System.currentTimeMillis()+"");
        rocketMessageDto.setTopic(TopicEnum.TOPIC_UAAC.getTopic());
        rocketMessageDto.setTag(TagEnum.UAAC_REGISTER_USER.getTag());
        rocketMessageDto.setBody(objectMapper.writeValueAsString(userRegisterDto));

        return producerApi.sendTransactionMessage(rocketMessageDto);
    }

    @RequestMapping(value = "/api/user/active",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String activateUser(@RequestParam("username") String userName, @RequestParam("key") String key, @RequestParam("timeStamp") String timeStamp, HttpServletRequest request) throws JsonProcessingException {
        String userActiveUrl=HttpConstants.USER_ACTIVE_URL+"?username="+userName
                +"&key="+key+"&timeStamp="+timeStamp;
        String toCompare=(String)redisTemp.opsForValue().get(userName);
        if(userActiveUrl.equals(toCompare)&&userService.activateUser(userName)){
            return ResponseWrapper.ok("activated user successfully");
        }
        return ResponseWrapper.error(500,"the activation url has been expired");
    }

    @RequestMapping(value = "/auth/user/lock",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @NeedSuperAdmin
    public String lockUser(@RequestParam("username") String username) throws JsonProcessingException {
        if(userService.lockUser(username)){
            return ResponseWrapper.ok("locked user "+username+" successfully");
        }else{
            return ResponseWrapper.error(ResponseStatusEnum.NEED_SUPER_ADMIN.getCode(),
                    ResponseStatusEnum.NEED_SUPER_ADMIN.getMessage());
        }
    }

    @RequestMapping(value = "/auth/user/info/basic",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getUserBasicInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String loginToken=request.getHeader("loginToken");
        if(!tokenService.isLoginTokenValid(loginToken)){
            //the login token is invalid which means the user has not logged in yet
            ResponseWrapper.error(ResponseStatusEnum.LOGIN_TOKEN_INVALID.getCode(),ResponseStatusEnum.LOGIN_TOKEN_INVALID.getMessage(),response);
            return;
        }
        LogUtil.info("found login token on the request:"+loginToken);
        UserBasicInfoDto info=userService.getUserInfoBasic(loginToken);
        if(info==null){
            ResponseWrapper.error(ResponseStatusEnum.BASIC_INFO_NOT_FOUND.getCode(),ResponseStatusEnum.BASIC_INFO_NOT_FOUND.getMessage(),response);
            return;
        }
        Map<String,Object> params=new HashMap<>(4);
        params.put("status",200);
        params.put("userName",info.getUserName());
        params.put("avatar",info.getAvatar());
        params.put("phone",info.getMobilePhone());
        ResponseWrapper.response(params,response);
    }
}
