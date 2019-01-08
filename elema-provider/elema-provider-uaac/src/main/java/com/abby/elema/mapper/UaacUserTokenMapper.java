package com.abby.elema.mapper;

import com.abby.elema.model.domain.UaacUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UaacUserTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UaacUserToken record);

    int insertSelective(UaacUserToken record);

    UaacUserToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UaacUserToken record);

    int updateByPrimaryKey(UaacUserToken record);

    UaacUserToken getOnlineByAccessToken(String accessToken);

    List<UaacUserToken> getByAccessToken(String accessToken);

    UaacUserToken getOnlineByName(String userName);
}