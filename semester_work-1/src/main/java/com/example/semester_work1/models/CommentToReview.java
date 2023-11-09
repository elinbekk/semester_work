package com.example.semester_work1.models;

public class CommentToReview {
    private Integer commentId;
    private Integer reviewId;
    private Integer authorId;
    private String text;
    private String date;
    private String authorFullname;

    public CommentToReview(Integer commentId, Integer reviewId, Integer authorId, String text, String date, String authorFullname) {
        this.commentId = commentId;
        this.reviewId = reviewId;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
        this.authorFullname = authorFullname;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
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
