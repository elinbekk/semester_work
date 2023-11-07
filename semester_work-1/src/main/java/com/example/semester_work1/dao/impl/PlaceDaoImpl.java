package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.PlaceDao;
import com.example.semester_work1.models.FavouritePlace;
import com.example.semester_work1.models.Place;
import com.example.semester_work1.utils.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceDaoImpl implements PlaceDao {
    @Override
    public void save(Place item) {

    }

    @Override
    public List<Place> getAll() {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from places"
            );
            ResultSet rs = statement.executeQuery();
            List<Place> places = new ArrayList<>();
            while (rs.next()) {
                Place place = new Place(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDouble(7),
                        rs.getString(8)
                );
                places.add(place);
            }
            return places;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Place> getById(String id) {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from places where place_id = ?"
            );
            statement.setInt(1, Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Place place = new Place(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDouble(7),
                        rs.getString(8)
                );
                return Optional.of(place);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Place item) {

    }

    public List<Place> getPlaceLikeByQuery(String place){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from places where place_title like ?"
            );
            statement.setString(1, "%" + place + "%");
            ResultSet rs = statement.executeQuery();
            List<Place> places = new ArrayList<>();
            while (rs.next()) {
                Place p = new Place(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDouble(7),
                        rs.getString(8)
                );
                places.add(p);
            }
            return places;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
