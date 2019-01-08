package com.abby.elema.model;

/**
 * @author: Abby
 */
public enum  EmailTemplateEnums {

    RESET_PASSWORD_TEMPLATE("RESET_PASSWORD","resetPasswordEn.ftl"),
    USER_ACTIVATION_TEMPLATE("USER ACTIVATION","activeUserEN.ftl");

    private String type;
    private String templateName;

    EmailTemplateEnums(String type,String templateName){
        this.type=type;
        this.templateName=templateName;
    }

    public String getType(){
        return type;
    }

    public String getTemplateName(){
        return templateName;
    }
}
