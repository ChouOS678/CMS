package com.cms.controller;

import com.cms.common.JwtUtil;
import com.cms.common.Result;
import com.cms.entity.Activity;
import com.cms.entity.ActivityParticipant;
import com.cms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result<Activity> createActivity(@RequestBody Activity activity) {
        // Should check for club admin role
        return Result.success(activityService.createActivity(activity));
    }

    @GetMapping
    public Result<List<Activity>> getAllActivities() {
        return Result.success(activityService.getAllActivities());
    }

    @GetMapping("/{id}")
    public Result<Activity> getActivityById(@PathVariable Long id) {
        return Result.success(activityService.getActivityById(id));
    }

    @PutMapping("/{id}")
    public Result<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        // Should check for club admin role
        activity.setId(id);
        return Result.success(activityService.updateActivity(activity));
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteActivity(@PathVariable Long id) {
        // Should check for club admin role
        activityService.deleteActivity(id);
        return Result.success();
    }

    @PostMapping("/{id}/signup")
    public Result<Void> signup(@PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Long userId = jwtUtil.getUserIdFromToken(token);
            activityService.signup(userId, id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(400, e.getMessage());
        }
    }

    @GetMapping("/{id}/participants")
    public Result<List<ActivityParticipant>> getParticipants(@PathVariable Long id) {
        return Result.success(activityService.getParticipants(id));
    }
}
