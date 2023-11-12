package com.example.semester_work1.models;

import java.util.List;
import java.util.UUID;

public class Review {
    private UUID reviewId;
    private UUID authorId;
    private String text;
    private int assessment;
    private String date;
    private UUID placeId;
    private String authorFullName;
    private List<CommentToReview> commentsList;

    public Review(UUID reviewId, UUID authorId, String text, int assessment, String date, UUID idOfPlace, String fullName) {
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.assessment = assessment;
        this.date = date;
        this.placeId = idOfPlace;
        this.authorFullName = fullName;
    }

    public Review(UUID reviewId, UUID authorId, String text, int assessment, String date, UUID placeId, String authorFullName, List<CommentToReview> commentsList) {
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.assessment = assessment;
        this.date = date;
        this.placeId = placeId;
        this.authorFullName = authorFullName;
        this.commentsList = commentsList;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public UUID getAuthorId() {
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

    public UUID getPlaceId() {
        return placeId;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }
    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public void setAuthorId(UUID authorId) {
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

    public void setPlaceId(UUID idOfPlace) {
        this.placeId = idOfPlace;
    }

    public void setCommentsList(List<CommentToReview> commentsList) {
        this.commentsList = commentsList;
    }

    public List<CommentToReview> getCommentsList() {
        return commentsList;
    }
}
