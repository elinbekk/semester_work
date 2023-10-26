package com.example.semester_work1.models;

public class User {
    private String userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String photoRef;

    public User(String userId, String name, String lastName, String email, String password, String photoRef) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.photoRef = photoRef;
    }

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
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

    public String getPassword() {
        return password;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setUserId(String userId) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }
}
