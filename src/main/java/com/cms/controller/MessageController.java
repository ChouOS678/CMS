package com.cms.controller;

import com.cms.common.JwtUtil;
import com.cms.common.Result;
import com.cms.entity.Message;
import com.cms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result<Message> sendMessage(@RequestHeader("Authorization") String authHeader,
            @RequestBody Message message) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long senderId = jwtUtil.getUserIdFromToken(token);
            message.setSenderId(senderId);
            return Result.success(messageService.sendMessage(message));
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @GetMapping("/conversations/{userId}")
    public Result<List<Message>> getConversation(@PathVariable Long userId,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long currentUserId = jwtUtil.getUserIdFromToken(token);
            return Result.success(messageService.getConversation(currentUserId, userId));
        } catch (Exception e) {
            return Result.error(401, "Unauthorized");
        }
    }

    @PostMapping("/read")
    public Result<Void> markAsRead(@RequestBody Map<String, Long> request) {
        Long messageId = request.get("messageId");
        messageService.markAsRead(messageId);
        return Result.success();
    }
}
