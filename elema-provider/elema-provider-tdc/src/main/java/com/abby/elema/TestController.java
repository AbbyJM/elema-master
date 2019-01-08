package com.abby.elema;

import com.abby.elema.exception.RocketMqException;
import com.abby.elema.interfaces.EmailApi;
import com.abby.elema.model.dto.MailMessageDto;
import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.TopicEnum;
import com.abby.elema.model.enums.UserGroupEnum;
import com.abby.elema.mq.RocketmqProducer;
import com.abby.elema.util.LogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Abby
 */
@RestController
public class TestController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RocketmqProducer producer;

    @Resource
    private EmailApi emailApi;

    @RequestMapping(value = "/test")
    public void test() throws RocketMqException, IOException, TemplateException, MessagingException {

        UserRegisterDto userRegisterDto=new UserRegisterDto();
        userRegisterDto.setName("zhangjianming");
        userRegisterDto.setPassword("123456788");
        userRegisterDto.setGroup(UserGroupEnum.DEVELOPMENT.getUserGroup());
        List<String> roles=new ArrayList<>();
        roles.add("DEVELOPER");
        userRegisterDto.setRoles(roles);
        String jsonStr=objectMapper.writeValueAsString(userRegisterDto);

        SendResult result=producer.sendTransactionMessage("Uaac","register_user","jianming",jsonStr,TopicEnum.TOPIC_UAAC.getTopic());
        LogUtil.info("transactionState "+((TransactionSendResult) result).getLocalTransactionState());

    }
}
