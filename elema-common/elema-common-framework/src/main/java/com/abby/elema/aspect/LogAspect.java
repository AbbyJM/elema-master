package com.abby.elema.aspect;

import com.abby.elema.InstanceUrlsProperties;
import com.abby.elema.annotation.SaveLog;
import com.abby.elema.model.constants.HttpConstants;
import com.abby.elema.model.dto.EnvironmentDto;
import com.abby.elema.model.dto.LoginUserDto;
import com.abby.elema.model.dto.OperationDto;

import com.abby.elema.model.enums.ResponseStatusEnum;
import com.abby.elema.model.enums.UserGroupEnum;
import com.abby.elema.model.enums.UserRoleEnum;
import com.abby.elema.model.vo.TokenVo;
import com.abby.elema.util.LogUtil;
import com.abby.elema.util.RequestUtil;
import com.abby.elema.util.TokenUtil;
import com.abby.elema.wrapper.ResponseObject;
import com.abby.elema.wrapper.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.Token;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abby
 */

@Component
@Aspect
public class LogAspect {

    @Autowired
    private RedisTemplate<String,Object> redisTemp;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private InstanceUrlsProperties instanceUrlsProperties;

    private ThreadLocal<Date> threadLocal=new ThreadLocal<>();

    @Pointcut("@annotation(com.abby.elema.annotation.SaveLog)")
    public void logAnnotation(){
    }

    @Before("logAnnotation()")
    public void doBefore(){
        threadLocal.set(new Date(System.currentTimeMillis()));
    }

    @AfterReturning(pointcut = "logAnnotation()",returning = "returnValue")
    public void doAfter(JoinPoint joinPoint,Object returnValue) throws IOException {
        Date start=threadLocal.get();
        Date end=new Date(System.currentTimeMillis());
        HttpServletRequest request=RequestUtil.getRequest();
        EnvironmentDto environmentDto=new EnvironmentDto();
        if(request!=null){
            environmentDto.setIpAddress(RequestUtil.getIpAddress(request));
            environmentDto.setBrowserType(RequestUtil.getBrowser(request));
            environmentDto.setTargetOs(RequestUtil.getOs(request));
            environmentDto.setLocationName("localhost");
        }

        OperationDto operationDto=new OperationDto();
        operationDto.setTimeBegin(start);
        operationDto.setClassName(joinPoint.getTarget().getClass().getName());
        operationDto.setMethodName(joinPoint.getSignature().getName());
        operationDto.setTimeUsed((int) (end.getTime()-start.getTime()));
        operationDto.setEnvironmentDto(environmentDto);
        operationDto.setRequestUrl(request.getRequestURL().toString());

        ResponseObject response= null;
        try {
            response=ResponseWrapper.parse((String)returnValue);
        } catch (IOException e) {
            LogUtil.info("failed to convert string to json object,"+e.getMessage());
        }

        if(response!=null){
            operationDto.setStatus(ResponseStatusEnum.getMessage(response.getStatus()));
            operationDto.setLogMessage(response.getMessage());
        }

        String params=getArgsString(request);
        if(params!=null){
            operationDto.setRequestParams(params);
        }

        LoginUserDto loginUserDto = null;
        String loginToken=TokenUtil.extractLoginToken(request);

        if(loginToken!=null){
            loginUserDto=(LoginUserDto)redisTemp.opsForValue().get(loginToken);
        }

        if(loginUserDto!=null){
            operationDto.setOperatorName(loginUserDto.getUserName());
            operationDto.setOperatorId(loginUserDto.getUserId());
            operationDto.setUserGroupEnum(UserGroupEnum.UNKNOWN);
        }else{
            operationDto.setOperatorId(-1);
            operationDto.setOperatorName(UserRoleEnum.ROLE_ANONYMOUS.getUserRole());
            operationDto.setUserGroupEnum(UserGroupEnum.UNKNOWN);
        }

        SaveLog logAnnotation=findAnnotation(joinPoint);
        if(logAnnotation!=null){
            operationDto.setOperationType(logAnnotation.type());
        }

        //request the feign client token
        String token = instanceUrlsProperties.getAccessToken() + "?grant_type=client_credentials" +
                "&client_id=feign" + "&client_secret=elema-secret";
        ResponseEntity<String> tokenResponse=restTemplate.postForEntity(token,null,String.class);
        TokenVo tokenVo=objectMapper.readValue(tokenResponse.getBody(),TokenVo.class);

        HttpHeaders headers=new HttpHeaders();
        if(tokenVo!=null){
            headers.add("authorization","bearer "+tokenVo.getAccess_token());
        }
        HttpEntity<OperationDto> requestEntity=new HttpEntity<>(operationDto,headers);
        restTemplate.postForEntity(instanceUrlsProperties.getLog(),requestEntity,Object.class);
    }

    /**
     * find the method annotated with @SaveLog
     * @param joinPoint the jointPoint
     * @return the annotation
     */
    private SaveLog findAnnotation(JoinPoint joinPoint){
        Method[] methods=joinPoint.getTarget().getClass().getDeclaredMethods();
        String methodName=joinPoint.getSignature().getName();
        if(methods!=null&&methods.length>0){
            for(Method m:methods){
                if(m.getName().equals(methodName)){
                    return m.getAnnotation(SaveLog.class);
                }
            }
        }
        return null;
    }

    /**
     * get the parameters of the requests
     * @param request the request
     * @return the parameter string
     */
    private String getArgsString(HttpServletRequest request){
        Enumeration<String> params=request.getParameterNames();
        StringBuilder builder=new StringBuilder();
        while (params.hasMoreElements()){
            String param=params.nextElement();
            builder.append(param).append(": ").append(request.getParameter(param)).append(";  ");
        }
        return builder.toString();
    }

}