package com.cms.service.impl;

import com.cms.entity.Message;
import com.cms.mapper.MessageMapper;
import com.cms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message sendMessage(Message message) {
        message.setSendTime(new Date());
        message.setIsRead(false);
        messageMapper.insert(message);
        return message;
    }

    @Override
    public List<Message> getConversation(Long userId1, Long userId2) {
        return messageMapper.selectBySenderAndReceiver(userId1, userId2);
    }

    @Override
    public void markAsRead(Long messageId) {
        messageMapper.updateReadStatus(messageId);
    }
}
