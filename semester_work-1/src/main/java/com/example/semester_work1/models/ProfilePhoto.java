package com.example.semester_work1.models;

public class ProfilePhoto {
    private Integer userId;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;

    public ProfilePhoto(Integer userId, String originalFileName, String storageFileName, Long size, String type) {
        this.userId = userId;
        this.originalFileName = originalFileName;
        this.storageFileName = storageFileName;
        this.size = size;
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStorageFileName() {
        return storageFileName;
    }

    public void setStorageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
