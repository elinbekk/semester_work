package com.example.semester_work1.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {
    void save(T item) throws SQLException;
    List<T> getAll();
    Optional<T> getById(K id);
    void delete(K id);
    void update(T item);
}
