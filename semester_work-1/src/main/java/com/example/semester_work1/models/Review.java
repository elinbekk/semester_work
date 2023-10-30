package com.example.semester_work1.models;

import java.util.Date;

public class Review {

    private int reviewId;
    private String authorId;
    private String text;
    private int assessment;
    private String date;
    private int placeId;

    public Review(int reviewId, String authorId, String text, int assessment, String date, int idOfPlace) {
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.assessment = assessment;
        this.date = date;
        this.placeId = idOfPlace;
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

    public String getDate() {
        return date;
    }

    public int getPlaceId() {
        return placeId;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdOfPlace(int idOfPlace) {
        this.placeId = idOfPlace;
    }
}
