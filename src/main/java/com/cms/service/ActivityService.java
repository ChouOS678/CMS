package com.cms.service;

import com.cms.entity.Activity;
import com.cms.entity.ActivityParticipant;
import java.util.List;

public interface ActivityService {
    Activity createActivity(Activity activity);

    List<Activity> getAllActivities();

    Activity getActivityById(Long id);

    Activity updateActivity(Activity activity);

    void deleteActivity(Long id);

    void signup(Long userId, Long activityId);

    List<ActivityParticipant> getParticipants(Long activityId);
}
