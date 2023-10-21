package com.example.semester_work1.models;

public class User {
    private Integer userId;
    private String name;
    private String lastName;
    private String email;
    private String hashPassword;
    private String photoRef;

    public User(Integer userId, String name, String lastName, String email, String hashPassword, String photoRef) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.hashPassword = hashPassword;
        this.photoRef = photoRef;
    }

    public User(String name, String lastName, String email, String hashPassword) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }
}
