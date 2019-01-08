package com.abby.elema.model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Abby
 */
public enum  TopicEnum {

    TOPIC_UAAC("Uaac");

    private String topicName;

    TopicEnum(String topic){
        topicName=topic;
    }

    public String getTopic(){
        return topicName;
    }

    public static List<String> getTopics(){
        TopicEnum[] topicEnums=TopicEnum.values();
        List<String> topics=new ArrayList<>();
        for(TopicEnum topic:topicEnums){
            topics.add(topic.getTopic());
        }
        return topics;
    }
}