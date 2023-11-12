package com.example.semester_work1.dao;

import com.example.semester_work1.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao extends Dao<User, UUID> {
    void save(User item) throws SQLException;
    List<User> getAll();
    Optional<User> getById(UUID id);
    void delete(UUID id);
    void update(User item);
}
