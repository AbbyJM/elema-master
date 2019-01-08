package com.abby.elema.util;

import com.abby.elema.model.domain.TransactionMessage;
import com.google.inject.internal.util.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author: Abby
 */
public class MessageUtil {

    public static Message buildMessage(String topic,String tag,String key,String body){
        checkMessage(topic,tag,key,body);
        Message message=new Message();
        message.setTopic(topic);
        message.setTags(tag);
        message.setKeys(key);

        try {
            message.setBody(body.getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {

        }
        return message;
    }

    public static TransactionMessage buildTransactionMessage(String topic,String tag,String transactionId){
        TransactionMessage transactionMessage=new TransactionMessage();
        transactionMessage.setTopic(topic);
        transactionMessage.setTag(tag);
        transactionMessage.setTime(new Date());
        transactionMessage.setTransactionId(transactionId);
        return transactionMessage;
    }

    private static void checkMessage(String topic,String tag,String key,String body){
        Preconditions.checkArgument(StringUtils.isNotEmpty(topic),"topic cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(tag),"tag cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(key),"key cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(body),"body cannot be null");
    }

}
