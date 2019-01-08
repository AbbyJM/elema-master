package com.abby.elema.mapper;

import com.abby.elema.model.domain.UaacLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UaacLogMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UaacLog record);

    int insertSelective(UaacLog record);

    UaacLog selectByPrimaryKey(Integer id);

    List<UaacLog> getLogsOfType(String logType);

    List<UaacLog> getLogsOfUserName(String userName);

    int updateByPrimaryKeySelective(UaacLog record);

    int updateByPrimaryKey(UaacLog record);

    List<UaacLog> getAllLogs();
}