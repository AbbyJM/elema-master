package com.abby.elema.web;

import com.abby.elema.interfaces.UserApi;
import com.abby.elema.util.LogUtil;
import com.abby.elema.wrapper.ResponseObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: Abby
 */
@RestController
public class TestController {

    @Resource
    private UserApi userApi;

    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/test")
    public void test(){
        String isu="";
        try {
            isu = userApi.isUserSeller("jianming");
            ResponseObject response=objectMapper.readValue(isu,ResponseObject.class);
            if((boolean)response.getResult()){
                LogUtil.info("the user is seller");
            }else{
                LogUtil.info("the user is not seller");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
