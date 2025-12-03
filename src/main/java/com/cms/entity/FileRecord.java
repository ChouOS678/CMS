package com.cms.entity;

import java.util.Date;

public class FileRecord {
    private Long id;
    private Long uploaderId;
    private Long clubId;
    private String filename;
    private String filePath;
    private String fileType;
    private Date uploadTime;

    public FileRecord() {
    }

    public FileRecord(Long id, Long uploaderId, Long clubId, String filename, String filePath, String fileType,
                      Date uploadTime) {
        this.id = id;
        this.uploaderId = uploaderId;
        this.clubId = clubId;
        this.filename = filename;
        this.filePath = filePath;
        this.fileType = fileType;
        this.uploadTime = uploadTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
