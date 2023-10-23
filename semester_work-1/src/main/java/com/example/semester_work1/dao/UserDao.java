package com.example.semester_work1.dao;

import com.example.semester_work1.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User, Integer> {
    void save(User item) throws SQLException;
    List<User> getAll();
    Optional<User> getById(Integer id);
    void delete(Integer id);
    void update(User item);
}
