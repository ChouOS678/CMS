package com.cms.entity;

import java.util.Date;

public class ClubMember {
    private Long id;
    private Long clubId;
    private Long userId;
    private String role; // MEMBER, ADMIN, PRESIDENT
    private String status; // PENDING, APPROVED
    private Date joinTime;

    public ClubMember() {
    }

    public ClubMember(Long id, Long clubId, Long userId, String role, String status, Date joinTime) {
        this.id = id;
        this.clubId = clubId;
        this.userId = userId;
        this.role = role;
        this.status = status;
        this.joinTime = joinTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}
