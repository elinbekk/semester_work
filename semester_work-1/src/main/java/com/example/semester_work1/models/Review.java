package com.example.semester_work1.models;

import java.util.Date;

public class Review {
    private int reviewId;
    private String authorId;
    private String text;
    private int assessment;
    private Date date;
    private int idOfPlace;

    public Review(int reviewId, String authorId, String text, int assessment, Date date, int idOfPlace) {
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.assessment = assessment;
        this.date = date;
        this.idOfPlace = idOfPlace;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getText() {
        return text;
    }

    public int getAssessment() {
        return assessment;
    }

    public Date getDate() {
        return date;
    }

    public int getIdOfPlace() {
        return idOfPlace;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdOfPlace(int idOfPlace) {
        this.idOfPlace = idOfPlace;
    }
}
