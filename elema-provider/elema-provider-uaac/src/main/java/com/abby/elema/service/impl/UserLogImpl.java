package com.abby.elema.service.impl;

import com.abby.elema.mapper.UaacLogMapper;
import com.abby.elema.model.domain.UaacLog;

import com.abby.elema.model.dto.OperationDto;
import com.abby.elema.model.enums.OperationEnum;
import com.abby.elema.service.UserLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: Abby
 */
@Service
public class UserLogImpl implements UserLogService {

    @Autowired
    private UaacLogMapper logMapper;

    @Override
    public boolean saveLog(OperationDto operationDto) {
        UaacLog uaacLog=new UaacLog();
        uaacLog.setOperatorId(operationDto.getOperatorId());
        uaacLog.setOperatorName(operationDto.getOperatorName());
        uaacLog.setClassName(operationDto.getClassName());
        uaacLog.setMessage(operationDto.getLogMessage());
        uaacLog.setOperationType(operationDto.getOperationType().getType());
        uaacLog.setStatus(operationDto.getStatus());
        uaacLog.setRequestUrl(operationDto.getRequestUrl());
        uaacLog.setRequestParams(operationDto.getRequestParams());
        uaacLog.setMethodName(operationDto.getMethodName());
        uaacLog.setBrowserName(operationDto.getEnvironmentDto().getBrowserType());
        uaacLog.setIpAddress(operationDto.getEnvironmentDto().getIpAddress());
        uaacLog.setLocationName(operationDto.getEnvironmentDto().getLocationName());
        uaacLog.setTargetOs(operationDto.getEnvironmentDto().getTargetOs());
        uaacLog.setTimeUsed(operationDto.getTimeUsed());
        uaacLog.setTimeBegin(operationDto.getTimeBegin());
        uaacLog.setRequestUrl(operationDto.getRequestUrl());
        uaacLog.setRequestParams(operationDto.getRequestParams());
        return logMapper.insert(uaacLog)>0;
    }

    @Override
    public List<UaacLog> getLogsOfType(OperationEnum operationEnum) {
        if(operationEnum==null||operationEnum.getType()==null){
            return null;
        }
        return logMapper.getLogsOfType(operationEnum.getType());
    }

    @Override
    public List<UaacLog> getLogsOfUserName(String name) {
        if(name==null||name.isEmpty()){
            return null;
        }
        return logMapper.getLogsOfUserName(name);
    }

    @Override
    public List<UaacLog> getLogsBetween(Date from, Date to) {

        return null;
    }

    @Override
    public List<UaacLog> getLogsOfPage(int pageNum, int pageSize) {
        if(pageNum>0&&pageSize>0){
            PageHelper.startPage(pageNum,pageSize);
            return logMapper.getAllLogs();
        }
        return null;
    }
}
