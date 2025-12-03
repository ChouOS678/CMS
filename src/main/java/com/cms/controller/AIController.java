package com.cms.controller;

import com.cms.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    // Note: AI integration requires external API (QWen)
    // These are placeholder endpoints that return mock responses

    @PostMapping("/qwen-chat")
    public Result<Map<String, String>> qwenChat(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        Map<String, String> response = new HashMap<>();
        response.put("answer", "This is a placeholder response. Please integrate with QWen API.");
        return Result.success(response);
    }

    @PostMapping("/generate-activity-description")
    public Result<Map<String, String>> generateActivityDescription(@RequestBody Map<String, String> request) {
        String topic = request.get("topic");
        Map<String, String> response = new HashMap<>();
        response.put("description",
                "Generated description for: " + topic + ". Please integrate with QWen API for real generation.");
        return Result.success(response);
    }

    @GetMapping("/recommendations")
    public Result<Map<String, Object>> getRecommendations(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> response = new HashMap<>();
        response.put("message",
                "Placeholder recommendations. Please integrate with QWen API for personalized recommendations.");
        return Result.success(response);
    }
}
