package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.FavouritePlaceDao;
import com.example.semester_work1.models.FavouritePlace;
import com.example.semester_work1.utils.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FavouritePlaceDaoImpl implements FavouritePlaceDao {
    @Override
    public void save(FavouritePlace item) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("insert into favourite(fp_id, user_id, place_id) values (?, ?, ?)");
        statement.setObject(1, item.getId());
        statement.setObject(2, item.getUserId());
        statement.setObject(3, item.getPlaceId());
        statement.executeUpdate();
    }

    @Override
    public List<FavouritePlace> getAll() {
        return null;
    }

    @Override
    public Optional<FavouritePlace> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) throws SQLException {

    }


    @Override
    public void delete(UUID userId, UUID placeId) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("delete from favourite where user_id = ? and place_id = ?");
        statement.setObject(1, userId);
        statement.setObject(2, placeId);
        statement.executeUpdate();
    }

    @Override
    public void update(FavouritePlace item) {

    }
    public List<UUID> getUsersAllFavourite(UUID userId){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement("select * from favourite where user_id = ?");
            List<UUID> favourite = new ArrayList<>();
            statement.setObject(1, userId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                FavouritePlace fp = new FavouritePlace(
                        (UUID) rs.getObject(1),
                        (UUID) rs.getObject(2),
                        (UUID) rs.getObject(3)
                );
                favourite.add(fp.getPlaceId());
            }
            return favourite;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFavourite(UUID userId, UUID placeId) {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement("select * from favourite where user_id = ? and place_id = ?");
            statement.setObject(1, userId);
            statement.setObject(2, placeId);
            ResultSet rs = statement.executeQuery();
            return (rs.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
