package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.PlaceDao;
import com.example.semester_work1.models.FavouritePlace;
import com.example.semester_work1.models.Place;
import com.example.semester_work1.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceDaoImpl implements PlaceDao {
    @Override
    public Place save(Place item) {
        return null;
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
                        rs.getString(2)
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
    public FavouritePlace saveFp(FavouritePlace item) {
        return null;
    }

    @Override
    public boolean isFavourite(FavouritePlace fp) {
        return false;
    }


    @Override
    public List<FavouritePlace> getAllFavouritePlaces() {
        return null;
    }

    @Override
    public Optional<Place> getById(Integer id) {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from places where place_id = ?"
            );
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Place place = new Place(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)


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
    public void delete(Integer id) {

    }

    @Override
    public void update(Place item) {

    }
}
