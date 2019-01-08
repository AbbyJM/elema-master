package com.abby.elema.service;

import com.abby.elema.model.domain.UaacLog;
import com.abby.elema.model.dto.EnvironmentDto;
import com.abby.elema.model.dto.OperationDto;
import com.abby.elema.model.enums.OperationEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: Abby
 */
public interface UserLogService {

    /**
     * save the log to database
     * @param operationDto the operation dto
     * @return true if operated successfully
     */
    boolean saveLog(OperationDto operationDto);

    /**
     * get the logs by log type
     * @param operationEnum the log type enum
     * @return a list of logs
     */
    List<UaacLog> getLogsOfType(OperationEnum operationEnum);

    /**
     * get the logs by user name
     * @param name the user name
     * @return a list of logs
     */
    List<UaacLog> getLogsOfUserName(String name);


    List<UaacLog> getLogsBetween(Date from,Date to);

    List<UaacLog> getLogsOfPage(int pageNum,int pageSize);
}
