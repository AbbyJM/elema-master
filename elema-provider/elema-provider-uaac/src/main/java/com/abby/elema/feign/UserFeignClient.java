package com.abby.elema.feign;

import com.abby.elema.interfaces.UserApi;
import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.service.UserService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

/**
 * @author: Abby
 */
@RestController
public class UserFeignClient implements UserApi {

    @Resource
    private UserService userService;

    @Override
    public String registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        try{
            if(userService.registerUser(userRegisterDto)){
                return ResponseWrapper.ok("registered successfully");
            }else{
                return ResponseWrapper.error(ResponseStatusEnum.REGISTER_FAILED.getCode(),
                        ResponseStatusEnum.REGISTER_FAILED.getMessage());
            }
        }catch (Exception e){
            LogUtil.info("failed to register user "+e.getMessage());
            return null;
        }
    }

    @Override
    public String isUserSeller(String userName) throws JsonProcessingException {
        boolean isSeller= userService.isUserSeller(userName);
        return ResponseWrapper.ok("queried successfully",isSeller);
    }

    @Override
    public String isUserVerified(String userName) throws JsonProcessingException {
        //TODO
        return ResponseWrapper.ok("queried successfully",true);
    }
}
