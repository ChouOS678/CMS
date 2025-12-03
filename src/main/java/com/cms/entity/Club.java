package com.cms.entity;

import java.util.Date;

public class Club {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String avatar;
    private Long creatorId;
    private String status; // PENDING, APPROVED, REJECTED
    private Date createTime;
    private Date updateTime;

    public Club() {
    }

    public Club(Long id, String name, String description, String category, String avatar, Long creatorId,
                String status, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.avatar = avatar;
        this.creatorId = creatorId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
