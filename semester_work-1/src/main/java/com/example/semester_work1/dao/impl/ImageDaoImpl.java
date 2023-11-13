package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.ImageDao;
import com.example.semester_work1.models.Image;
import com.example.semester_work1.models.Review;
import com.example.semester_work1.utils.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ImageDaoImpl implements ImageDao {
    @Override
    public void save(Image item) throws SQLException {

    }

    @Override
    public List<Image> getAll() {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from images"
            );
            ResultSet rs = statement.executeQuery();
            List<Image> images= new ArrayList<>();
            while (rs.next()) {
                Image img = new Image(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        (UUID) rs.getObject(5)
                );
                images.add(img);
            }
            return images;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Image> getById(String id) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Image item) {

    }

    public List<Image> getImagesByPlaceId(UUID placeId) {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from images where place_id=?"
            );
            statement.setObject(1, placeId);
            ResultSet rs = statement.executeQuery();
            List<Image> imgs = new ArrayList<>();
            while (rs.next()) {
                Image img = new Image(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        (UUID) rs.getObject(5)
                );
                imgs.add(img);
            }
            return imgs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
