package com.abby.elema.mq;

import com.abby.elema.interfaces.UserApi;
import com.abby.elema.mapper.TransactionMessageMapper;
import com.abby.elema.model.domain.TransactionMessage;
import com.abby.elema.model.dto.UserRegisterDto;
import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.model.enums.TagEnum;
import com.abby.elema.model.enums.TopicEnum;
import com.abby.elema.model.enums.TransactionStateEnum;
import com.abby.elema.util.LogUtil;
import com.abby.elema.util.MessageUtil;

import com.abby.elema.wrapper.ResponseObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * @author: Abby
 */
@Component
public class UaacTransactionalListener implements TransactionListener {

    @Resource
    private UserApi userApi;

    @Autowired
    @Qualifier("objectMapper")
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionMessageMapper transactionMessageMapper;

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        String topic=msg.getTopic();
        String tag=msg.getTags();

        String body= null;
        try {
            body = new String(msg.getBody(),RemotingHelper.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String transactionId=msg.getTransactionId();
        TransactionMessage transactionMessage=MessageUtil.buildTransactionMessage(topic,tag,transactionId);

        LogUtil.info("executing transaction for message with topic "+topic+" and tag "+tag);
        if(topic.equals(TopicEnum.TOPIC_UAAC.getTopic())) {

            //we are registering the user
            if (tag.equals(TagEnum.UAAC_REGISTER_USER.getTag())) {

                try {
                    UserRegisterDto userRegisterDto=objectMapper.readValue(body,UserRegisterDto.class);
                    String response=userApi.registerUser(userRegisterDto);

                    ResponseObject responseObject=objectMapper.readValue(response,ResponseObject.class);
                    if(responseObject.getStatus()==ResponseStatusEnum.STATUS_SUCCESS.getCode()){
                        transactionMessage.setState(TransactionStateEnum.COMMIT.getState());
                        //make sure we save the transaction message into the database
                        if(transactionMessageMapper.insert(transactionMessage)>0){
                            LogUtil.info("message with transactionId "+transactionId+" commit successfully");
                           return LocalTransactionState.COMMIT_MESSAGE;
                        }
                    }else{
                        transactionMessage.setState(TransactionStateEnum.ROLLBACK.getState());
                        transactionMessageMapper.insert(transactionMessage);
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                } catch (IOException e) {
                    LogUtil.info("failed to read json value "+e.getMessage());
                }
            }
        }
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String transactionId=msg.getTransactionId();
        LogUtil.info("checking message of transactionId "+transactionId);
        TransactionMessage message=transactionMessageMapper.selectByTransactionId(transactionId);
        //check the transaction status on the database
        if(message.getState().equals(TransactionStateEnum.COMMIT.getState())){
            return LocalTransactionState.COMMIT_MESSAGE;
        }else if(message.getState().equals(TransactionStateEnum.ROLLBACK.getState())){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }else{
            return LocalTransactionState.UNKNOW;
        }
    }
}
