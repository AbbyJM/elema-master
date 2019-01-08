package com.abby.elema.model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Abby
 */
public enum  TagEnum {

    UAAC_REGISTER_USER("register_user");

    private String tagName;

    TagEnum(String tag){
        tagName=tag;
    }

    public String getTag(){
        return tagName;
    }

    public static List<String> getTags(){
        TagEnum[] tagEnums=TagEnum.values();
        List<String> tags=new ArrayList<>();
        for(TagEnum tagEnum:tagEnums){
            tags.add(tagEnum.getTag());
        }
        return tags;
    }
}