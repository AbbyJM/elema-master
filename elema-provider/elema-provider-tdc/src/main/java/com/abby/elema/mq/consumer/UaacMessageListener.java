package com.abby.elema.mq.consumer;

import com.abby.elema.interfaces.EmailApi;
import com.abby.elema.model.constants.EmailConstants;
import com.abby.elema.model.dto.MailMessageDto;
import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.TagEnum;
import com.abby.elema.model.enums.TopicEnum;
import com.abby.elema.util.LogUtil;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Abby
 */
@Component
public class UaacMessageListener implements MessageListenerConcurrently {

    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private EmailApi emailApi;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for(MessageExt msg:msgs){
            LogUtil.info("consuming message with transaction id "+msg.getTransactionId());
            String topic=msg.getTopic();
            String tag=msg.getTags();
            if(topic.equals(TopicEnum.TOPIC_UAAC.getTopic())){
                if(tag.equals(TagEnum.UAAC_REGISTER_USER.getTag())){
                    try {
                        String messageBody=new String(msg.getBody(),RemotingHelper.DEFAULT_CHARSET);
                        UserRegisterDto userRegisterDto=objectMapper.readValue(messageBody,UserRegisterDto.class);
                        MailMessageDto mailMessageDto=new MailMessageDto();
                        mailMessageDto.setUserName(userRegisterDto.getName());
                        mailMessageDto.setTo(userRegisterDto.getEmail());
                        mailMessageDto.setSubject("Registering elema");
                        mailMessageDto.setFrom(EmailConstants.DEFAULT_SENDER);

                        emailApi.sendUserActivationEmail(mailMessageDto);
                    } catch (Exception e) {
                        LogUtil.info("failed to consume message "+e.getMessage());
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
