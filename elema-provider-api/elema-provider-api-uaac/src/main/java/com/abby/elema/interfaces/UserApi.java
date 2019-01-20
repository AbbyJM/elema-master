package com.abby.elema.interfaces;

import com.abby.elema.annotation.SaveLog;
import com.abby.elema.feign.FeignClientOauth2Config;
import com.abby.elema.hystrix.UserFeignFallback;

import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.OperationEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;

/**
 * @author: Abby
 */
@FeignClient(name = "elema-provider-uaac",fallback = UserFeignFallback.class,configuration = FeignClientOauth2Config.class)
public interface UserApi {

    @RequestMapping(value = "/admin/user/register",method = RequestMethod.POST,produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SaveLog(type = OperationEnum.REGISTER_USER)
    String registerUser(@RequestBody UserRegisterDto userRegisterDto);

    @RequestMapping(value = "/admin/user/isseller",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String isUserSeller(@RequestParam("userName") String userName) throws JsonProcessingException;

    @RequestMapping(value = "/admin/user/isVerified",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String isUserVerified(@RequestParam("userName")String userName) throws JsonProcessingException;

    @RequestMapping(value = "/user/info/basic")
    void getUserBasicInfo();
}
