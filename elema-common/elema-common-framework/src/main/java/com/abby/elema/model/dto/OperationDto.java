package com.abby.elema.model.dto;

import com.abby.elema.model.enums.OperationEnum;
import com.abby.elema.model.enums.UserGroupEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: Abby
 */
public class OperationDto implements Serializable {

    private int operatorId;

    private String operatorName;

    private String logMessage;

    private List<String> operatorRoles;

    private OperationEnum operationType;

    private UserGroupEnum userGroupEnum;

    private String methodName;

    private String className;

    private EnvironmentDto environmentDto;

    private String status;

    private int timeUsed;

    private String requestParams;

    private Date timeBegin;

    private String requestUrl;
    public void setOperatorId(int id){
        operatorId=id;
    }

    public int getOperatorId(){
        return operatorId;
    }

    public void setOperatorName(String name){
        if(name==null){
            operatorName="USER";
        }else{
            operatorName=name;
        }
    }

    public String getOperatorName(){
        return operatorName;
    }

    public void setOperatorRoles(List<String> roles){
        operatorRoles=roles;
    }

    public List<String> getOperatorRoles(){
        return operatorRoles;
    }

    public void setOperationType(OperationEnum type){
        if(type==null){
            operationType=OperationEnum.UNKNOWN;
        }else{
            operationType=type;
        }
    }

    public OperationEnum getOperationType(){
        return operationType;
    }

    public void setLogMessage(String message){
        logMessage=message;
    }

    public String getLogMessage(){
        return logMessage;
    }

    public void setUserGroupEnum(UserGroupEnum groupEnum){
        userGroupEnum=groupEnum;
    }

    public UserGroupEnum getUserGroupEnum() {
        return userGroupEnum;
    }

    public void setMethodName(String name){
        methodName=name;
    }

    public String getMethodName(){
        return methodName;
    }

    public void setEnvironmentDto(EnvironmentDto environment){
        environmentDto=environment;
    }

    public EnvironmentDto getEnvironmentDto(){
        return environmentDto;
    }

    public void setClassName(String name){
        className=name;
    }

    public String getClassName(){
        return className;
    }


    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public int getTimeUsed(){
        return timeUsed;
    }

    public void setTimeUsed(int time){
        timeUsed=time;
    }

    public String getRequestParams(){
        return requestParams;
    }

    public void setRequestParams(String params){
        requestParams=params;
    }

    public String getRequestUrl(){
        return requestUrl;
    }

    public void setRequestUrl(String url){
        requestUrl=url;
    }

    public void setTimeBegin(Date time){
        timeBegin=time;
    }

    public Date getTimeBegin(){
        return timeBegin;
    }

    @Override
    public String toString(){
        return "operatorName "+operatorName+"\nopratorId "+operatorId
                +"\nlogMessage "+logMessage+"\noperationType "+operationType
                +"\nmethodName "+methodName;
    }
}
