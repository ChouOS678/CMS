package com.cms.entity;

import java.util.Date;

public class News {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String type; // NEWS, ANNOUNCEMENT
    private Long targetClubId; // Nullable
    private Date publishTime;

    public News() {
    }

    public News(Long id, String title, String content, Long authorId, String type, Long targetClubId,
                Date publishTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.type = type;
        this.targetClubId = targetClubId;
        this.publishTime = publishTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTargetClubId() {
        return targetClubId;
    }

    public void setTargetClubId(Long targetClubId) {
        this.targetClubId = targetClubId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
