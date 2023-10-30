package com.example.semester_work1.dao;

import com.example.semester_work1.models.Review;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ReviewDao extends Dao<Review, Integer> {
    void save(Review item) throws SQLException;
    List<Review> getAll();
    Optional<Review> getById(Integer id);
    void delete(Integer id);
    void update(Review item);
}
