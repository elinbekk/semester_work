package com.example.semester_work1.dao;

import com.example.semester_work1.models.ProfilePhoto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProfilePhotoDao extends Dao<ProfilePhoto, String>{
    @Override
    void save(ProfilePhoto item) throws SQLException;

    @Override
    List<ProfilePhoto> getAll();

    @Override
    Optional<ProfilePhoto> getById(String id);

    @Override
    void delete(String id);

    @Override
    void update(ProfilePhoto item);
}
