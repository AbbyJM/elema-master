package com.abby.elema.mapper;

import com.abby.elema.model.domain.UaacRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UaacRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UaacRole record);

    int insertSelective(UaacRole record);

    UaacRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UaacRole record);

    int updateByPrimaryKey(UaacRole record);

    List<UaacRole> getRolesOfUserId(int id);

    List<UaacRole> getRolesOfUserName(String name);
}