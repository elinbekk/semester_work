package com.example.semester_work1.models;

import java.util.UUID;

public class CommentToReview {
    private UUID commentId;
    private UUID reviewId;
    private UUID authorId;
    private String text;
    private String date;
    private String authorFullname;

    public CommentToReview(UUID commentId, UUID reviewId, UUID authorId, String text, String date, String authorFullname) {
        this.commentId = commentId;
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
        this.authorFullname = authorFullname;
    }

    public CommentToReview(UUID reviewId, UUID authorId, String text, String date, String authorFullname) {
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
        this.authorFullname = authorFullname;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorFullname() {
        return authorFullname;
    }

    public void setAuthorFullname(String authorFullname) {
        this.authorFullname = authorFullname;
    }
}
