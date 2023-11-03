package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.ProfilePhotoDao;
import com.example.semester_work1.models.ProfilePhoto;
import com.example.semester_work1.utils.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProfilePhotoDaoImpl implements ProfilePhotoDao {
    @Override
    public void save(ProfilePhoto item) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("insert into profile_photo(user_id, original_name, storage_name, size, type) values (?, ?, ?, ?, ?)");
        statement.setString(1, item.getUserId());
        statement.setString(2, item.getOriginalFileName());
        statement.setString(3, item.getStorageFileName());
        statement.setLong(4, item.getSize());
        statement.setString(5, item.getType());
        statement.executeUpdate();
    }

    @Override
    public List<ProfilePhoto> getAll() {
        return null;
    }

    @Override
    public Optional<ProfilePhoto> getById(String id) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(ProfilePhoto item) {

    }
    public Optional<ProfilePhoto> getPhotoByUserId(String userId) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("select * from profile_photo where user_id=?");
        statement.setString(1, userId);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            ProfilePhoto pp = new ProfilePhoto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getString(5)
            );
            return Optional.of(pp);
        }
        else{
            return Optional.empty();
        }
    }
}
