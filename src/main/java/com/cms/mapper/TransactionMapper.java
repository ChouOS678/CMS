package com.cms.mapper;

import com.cms.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {
    int insert(Transaction transaction);

    List<Transaction> selectByClubId(Long clubId);

    int update(Transaction transaction);

    int deleteById(Long id);
}
