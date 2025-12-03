package com.cms.mapper;

import com.cms.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    int insert(Message message);

    List<Message> selectBySenderAndReceiver(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    int updateReadStatus(@Param("id") Long id);
}
