package com.cms.service.impl;

import com.cms.entity.Activity;
import com.cms.entity.ActivityParticipant;
import com.cms.mapper.ActivityMapper;
import com.cms.mapper.ActivityParticipantMapper;
import com.cms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityParticipantMapper participantMapper;

    @Override
    public Activity createActivity(Activity activity) {
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        activityMapper.insert(activity);
        return activity;
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityMapper.selectAll();
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityMapper.selectById(id);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        activity.setUpdateTime(new Date());
        activityMapper.update(activity);
        return activityMapper.selectById(activity.getId());
    }

    @Override
    public void deleteActivity(Long id) {
        activityMapper.deleteById(id);
    }

    @Override
    public void signup(Long userId, Long activityId) {
        ActivityParticipant existing = participantMapper.selectByActivityIdAndUserId(activityId, userId);
        if (existing != null) {
            throw new RuntimeException("Already signed up");
        }
        ActivityParticipant participant = new ActivityParticipant();
        participant.setActivityId(activityId);
        participant.setUserId(userId);
        participant.setStatus("SIGNED_UP");
        participant.setSignupTime(new Date());
        participantMapper.insert(participant);
    }

    @Override
    public List<ActivityParticipant> getParticipants(Long activityId) {
        return participantMapper.selectByActivityId(activityId);
    }
}
