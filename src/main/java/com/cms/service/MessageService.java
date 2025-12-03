package com.cms.service;

import com.cms.entity.Message;
import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);

    List<Message> getConversation(Long userId1, Long userId2);

    void markAsRead(Long messageId);
}
