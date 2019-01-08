package com.abby.elema.feign;

import com.abby.elema.interfaces.LogFeignApi;


import com.abby.elema.model.dto.EnvironmentDto;
import com.abby.elema.model.dto.OperationDto;
import com.abby.elema.model.enums.OperationEnum;
import com.abby.elema.service.UserLogService;


import com.abby.elema.util.LogUtil;
import com.abby.elema.util.RequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author: Abby
 */
@RestController
public class LogFeignClient implements LogFeignApi {

    @Autowired
    private UserLogService logService;

    @Override
    public void saveLog(@RequestBody OperationDto operationDto) {

        if(logService.saveLog(operationDto)){
            LogUtil.info("saved log for "+operationDto.getOperatorName());
        }
    }
}
