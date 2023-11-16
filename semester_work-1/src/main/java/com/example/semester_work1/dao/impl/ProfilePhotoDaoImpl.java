package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.ProfilePhotoDao;
import com.example.semester_work1.models.ProfilePhoto;
import com.example.semester_work1.utils.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfilePhotoDaoImpl implements ProfilePhotoDao {
    @Override
    public void save(ProfilePhoto item) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("insert into profile_photo(user_id, original_name, storage_name, size, type) values (?, ?, ?, ?, ?)");
        statement.setObject(1, item.getUserId());
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
    public Optional<ProfilePhoto> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {
        PreparedStatement statement = null;
        try {
            statement = JDBCConnection.getConn().prepareStatement("delete from profile_photo where user_id = ?");
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ProfilePhoto item) {

    }
    public ProfilePhoto getPhotoByUserId(UUID userId) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("select * from profile_photo where user_id=?");
        statement.setObject(1, userId);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            ProfilePhoto pp = new ProfilePhoto(
                    (UUID) rs.getObject(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getLong(4),
                    rs.getString(5)
            );
            return pp;
        }
        else{
            return null;
        }
    }
}
