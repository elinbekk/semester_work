package com.example.semester_work1.dao;

import com.example.semester_work1.models.CommentToReview;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CommentToReviewDao extends Dao<CommentToReview, Integer> {
    void save(CommentToReview item) throws SQLException;
    List<CommentToReview> getAll();
    Optional<CommentToReview> getById(Integer id);
    void delete(Integer id);
    void update(CommentToReview item);
}
