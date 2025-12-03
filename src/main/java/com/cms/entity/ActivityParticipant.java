package com.cms.entity;

import java.util.Date;

public class ActivityParticipant {
    private Long id;
    private Long activityId;
    private Long userId;
    private String status; // SIGNED_UP, CHECKED_IN
    private Date signupTime;

    public ActivityParticipant() {
    }

    public ActivityParticipant(Long id, Long activityId, Long userId, String status, Date signupTime) {
        this.id = id;
        this.activityId = activityId;
        this.userId = userId;
        this.status = status;
        this.signupTime = signupTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Date signupTime) {
        this.signupTime = signupTime;
    }
}
