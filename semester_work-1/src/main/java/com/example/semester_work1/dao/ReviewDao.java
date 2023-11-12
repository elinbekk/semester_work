package com.example.semester_work1.dao;

import com.example.semester_work1.models.Review;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewDao extends Dao<Review, UUID> {
    void save(Review item) throws SQLException;
    List<Review> getAll();
    Optional<Review> getById(UUID id);
    void delete(UUID id);
    void update(Review item);
}
