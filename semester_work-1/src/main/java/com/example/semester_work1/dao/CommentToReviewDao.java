package com.example.semester_work1.dao;

import com.example.semester_work1.models.CommentToReview;

import java.util.List;
import java.util.Optional;

public interface CommentToReviewDao extends Dao<CommentToReview, Integer> {
    CommentToReview save(CommentToReview item);
    List<CommentToReview> getAll();
    Optional<CommentToReview> getById(Integer id);
    void delete(Integer id);
    void update(CommentToReview item);
}
