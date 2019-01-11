package com.abby.elema.web;

import com.abby.elema.ElemaProperties;
import com.abby.elema.annotation.NeedSuperAdmin;
import com.abby.elema.annotation.SaveLog;
import com.abby.elema.exception.RocketMqException;
import com.abby.elema.interfaces.MQProducerApi;
import com.abby.elema.mapper.UaacLogMapper;
import com.abby.elema.mapper.UaacUserMapper;
import com.abby.elema.model.domain.UaacLog;
import com.abby.elema.model.dto.MailMessageDto;
import com.abby.elema.model.dto.RocketMessageDto;
import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.OperationEnum;
import com.abby.elema.model.enums.UserGroupEnum;
import com.abby.elema.service.EmailService;
import com.abby.elema.service.UserLogService;
import com.abby.elema.util.LogUtil;
import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * @author: Abby
 */
@RestController
public class TestController {

    Logger logger= Logger.getLogger(TestController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ElemaProperties properties;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UaacLogMapper mapper;
    @Resource
    private EmailService emailService;

    @Resource
    private MQProducerApi producerApi;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private UserLogService logService;

    @Autowired
    private UaacUserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/test")
    @SaveLog(type = OperationEnum.TEST)
    public String test(HttpServletResponse response) throws JsonProcessingException, RocketMqException {

        /*UserRegisterDto userRegisterDto=new UserRegisterDto();
        userRegisterDto.setName("zhangjianming");
        userRegisterDto.setPassword("123456788");
        userRegisterDto.setGroup(UserGroupEnum.DEVELOPMENT.getUserGroup());
        List<String> roles=new ArrayList<>();
        roles.add("DEVELOPER");
        userRegisterDto.setRoles(roles);
        String jsonStr=objectMapper.writeValueAsString(userRegisterDto);

        RocketMessageDto rocketMessageDto=new RocketMessageDto();
        rocketMessageDto.setTopic("Uaac");
        rocketMessageDto.setTag("register_user");
        rocketMessageDto.setBody(jsonStr);
        rocketMessageDto.setKey("testkey");
        rocketMessageDto.setProducerId("Uaac");
        return producerApi.sendTransactionMessage(rocketMessageDto);
        */
        //return userMapper.getUserNameById(1);
        Map<String,Object> params=new HashMap<>(3);
        params.put("status",200);
        params.put("message","test success");
        return objectMapper.writeValueAsString(params);
    }

    @RequestMapping("/protected")
    public String protectedResource(){
        return "protected resource";
    }

    @RequestMapping("/super")
    @NeedSuperAdmin
    public String superAdmin(){
        return "super admin";
    }

    @RequestMapping(value = "/test/email",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String send() throws IOException, TemplateException, MessagingException {

        MailMessageDto mailMessageDto=new MailMessageDto();
        mailMessageDto.setFrom("elemaMaster@163.com");

        mailMessageDto.setTo("1358890401@qq.com");
        mailMessageDto.setSubject("elema master activatinfo");
        mailMessageDto.setUserName("abby");
        mailMessageDto.setText("your account needs to be activated,if you do not activate ");
        taskExecutor.execute(()->{
            try {
                emailService.sendUserActivationEmail(mailMessageDto);
            }catch (Exception e){
                LogUtil.info("failed to send email");
            }
        });

        // return success immediately
        return ResponseWrapper.ok("send email successfully");
    }

    @RequestMapping(value = "/test/logs")
    public List<UaacLog> getLogs(){
        List<UaacLog> logs= logService.getLogsOfPage(2,10);
        LogUtil.info(logs.size()+"");
        return logs;
    }

    @RequestMapping(value = "/test/t")
    public void t(){

    }
}
