package com.abby.elema.mapper;

import com.abby.elema.model.domain.TransactionMessage;
import com.abby.elema.model.enums.TransactionStateEnum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TransactionMessageMapper {
    int insert(TransactionMessage record);

    int insertSelective(TransactionMessage record);

    TransactionMessage selectByPrimaryKey(Integer id);

    TransactionMessage selectByTransactionId(String transactionId);

    int updateByPrimaryKeySelective(TransactionMessage record);

    int updateByPrimaryKey(TransactionMessage record);
}