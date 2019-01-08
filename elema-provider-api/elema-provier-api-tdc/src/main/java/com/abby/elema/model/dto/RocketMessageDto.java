package com.abby.elema.model.dto;

/**
 * @author: Abby
 */
public class RocketMessageDto {
    private String topic;
    private String tag;
    private String key;
    private String body;
    private String producerId;

    public void setTopic(String topic){
        this.topic=topic;
    }

    public String getTopic(){
        return topic;
    }

    public void setTag(String tag){
        this.tag=tag;
    }

    public String getTag(){
        return tag;
    }

    public void setKey(String key){
        this.key=key;
    }

    public String getKey(){
        return key;
    }

    public void setBody(String body){
        this.body=body;
    }

    public String getBody(){
        return body;
    }

    public void setProducerId(String producerId){
        this.producerId=producerId;
    }

    public String getProducerId(){
        return producerId;
    }
}

