package com.example.semester_work1.dao;

import com.example.semester_work1.models.Image;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ImageDao extends Dao<Image, String>{
    @Override
    void save(Image item) throws SQLException;

    @Override
    List<Image> getAll();

    @Override
    Optional<Image> getById(String id);

    @Override
    void delete(String id);

    @Override
    void update(Image item);
}