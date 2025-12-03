package com.cms.entity;

import java.util.Date;

public class User {
    private Long id;
    private String username;
    private String password;
    private String role; // USER, ADMIN, SUPER_ADMIN
    private String email;
    private String introduction;
    private String avatar;
    private Date createTime;
    private Date updateTime;

    public User() {
    }

    public User(Long id, String username, String password, String role, String email, String introduction,
                String avatar, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.introduction = introduction;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
