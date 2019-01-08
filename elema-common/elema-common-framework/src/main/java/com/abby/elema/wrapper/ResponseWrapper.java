package com.abby.elema.wrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abby
 */
@Component
public class ResponseWrapper {

    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper mapper){
        ResponseWrapper.objectMapper=mapper;
    }

    public static ResponseObject parse(String jsonStr) throws IOException {
        return objectMapper.readValue(jsonStr,ResponseObject.class);
    }

    public static void ok(String message, HttpServletResponse response) throws IOException {
       send(200,message,response);
    }

    public static String ok(String message) throws JsonProcessingException {
        return generate(200,message,null);
    }

    public static String ok(String message,Object result) throws JsonProcessingException {
        return generate(200,message,result);
    }

    public static void response(Map<String,Object> result,HttpServletResponse response) throws IOException {
        send(result,response);
    }

    public static String response(Map<String,Object> result) throws JsonProcessingException {
        return generate(result);
    }

    public static void error(int code,String message,HttpServletResponse response) throws IOException {
       send(code,message,response);
    }

    public static String error(int code,String message,Object result) throws JsonProcessingException {
       return generate(code,message,result);
    }

    public static String error(int code,String message) throws JsonProcessingException {
        return generate(code,message,null);
    }

    private static void send(int statusCode,String message,HttpServletResponse response) throws IOException {
        Map<String,Object> result=new HashMap<>(3);
        result.put("status",statusCode);
        result.put("message",message);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    private static String generate(int statusCode,String message,Object resultObject) throws JsonProcessingException {
        Map<String,Object> result=new HashMap<>(3);
        result.put("status",statusCode);
        result.put("message",message);
        result.put("result",resultObject);

        return objectMapper.writeValueAsString(result);
    }

    private static void send(Map<String,Object>result,HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    private static String generate(Map<String,Object> result) throws JsonProcessingException {
        return objectMapper.writeValueAsString(result);
    }

}
