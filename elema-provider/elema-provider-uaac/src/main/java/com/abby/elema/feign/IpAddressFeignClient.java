package com.abby.elema.feign;


import com.abby.elema.interfaces.IpAddressApi;
import com.abby.elema.model.GaodeIpVo;
import com.abby.elema.model.constants.GaodeConstants;

import com.abby.elema.util.LogUtil;
import com.abby.elema.util.RegExpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


/**
 * @author: Abby
 */
@RestController
public class IpAddressFeignClient implements IpAddressApi {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * get the ip address location
     * @param ip the ip address
     * @return the GaodeIpVo
     */
    @Override
    public GaodeIpVo getIpLocation(String ip) {
        if(!RegExpUtil.isIpAddress(ip)){
            LogUtil.info("the parameter ip is not in correct format");
            return null;
        }
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("https://restapi.amap.com/v3/ip?key="+GaodeConstants.GAODE_WEB_KEY+"&ip="+ip+"&output=json",String.class);
        String responseJson=responseEntity.getBody();
        try {
            return objectMapper.readValue(responseJson,GaodeIpVo.class);
        } catch (IOException e) {
            LogUtil.info("failed to convert the json string to object","the error message is "+e.getMessage());
            return null;
        }
    }
}
