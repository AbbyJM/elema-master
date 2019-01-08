package com.abby.elema.hystrix;

import com.abby.elema.interfaces.LogFeignApi;
import com.abby.elema.model.dto.EnvironmentDto;
import com.abby.elema.model.dto.OperationDto;
import com.abby.elema.model.enums.OperationEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Abby
 */
public class LogFeignFallback implements LogFeignApi {


    @Override
    public void saveLog(OperationDto operationDto) {

    }


}
