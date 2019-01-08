package com.abby.elema.mapper;

import com.abby.elema.model.domain.UaacUser;
import com.abby.elema.model.domain.UaacUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UaacUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UaacUser record);

    int insertSelective(UaacUser record);

    UaacUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UaacUser record);

    int updateByPrimaryKey(UaacUser record);

    UaacUser findUserByName(String userName);

    UaacUser findUserByEmail(String email);

    String getUserNameById(int userId);

    List<UaacUser> getAllUsers();
}