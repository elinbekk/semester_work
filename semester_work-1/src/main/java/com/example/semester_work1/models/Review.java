package com.example.semester_work1.models;

import java.util.List;

public class Review {
    private int reviewId;
    private Integer authorId;
    private String text;
    private int assessment;
    private String date;
    private int placeId;
    private String authorFullName;
    private List<CommentToReview> commentsList;

    public Review(int reviewId, Integer authorId, String text, int assessment, String date, int idOfPlace, String fullName) {
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.assessment = assessment;
        this.date = date;
        this.placeId = idOfPlace;
        this.authorFullName = fullName;
    }

    public Review(int reviewId, Integer authorId, String text, int assessment, String date, int placeId, String authorFullName, List<CommentToReview> commentsList) {
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.assessment = assessment;
        this.date = date;
        this.placeId = placeId;
        this.authorFullName = authorFullName;
        this.commentsList = commentsList;
    }

    public int getReviewId() {
        return reviewId;
    }

    public Integer getAuthorId() {
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

    public String getAuthorFullName() {
        return authorFullName;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setAuthorId(Integer authorId) {
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

    public void setCommentsList(List<CommentToReview> commentsList) {
        this.commentsList = commentsList;
    }

    public List<CommentToReview> getCommentsList() {
        return commentsList;
    }
}
