package com.example.semester_work1.dao;

import com.example.semester_work1.models.ProfilePhoto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfilePhotoDao extends Dao<ProfilePhoto, UUID>{
    @Override
    void save(ProfilePhoto item) throws SQLException;

    @Override
    List<ProfilePhoto> getAll();

    @Override
    Optional<ProfilePhoto> getById(UUID id);

    @Override
    void delete(UUID id);

    @Override
    void update(ProfilePhoto item);
}
