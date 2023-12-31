package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.ReviewDao;
import com.example.semester_work1.models.Review;
import com.example.semester_work1.utils.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReviewDaoImpl implements ReviewDao {
    @Override
    public void save(Review item) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("insert into reviews(review_id, author_id, review_text, assessment, review_date, place_id, author_fullname) values (?, ?, ?, ?, ?, ?, ?)");
        statement.setObject(1, item.getReviewId());
        statement.setObject(2, item.getAuthorId());
        statement.setString(3, item.getText());
        statement.setInt(4, item.getAssessment());
        statement.setString(5, item.getDate());
        statement.setObject(6, item.getPlaceId());
        statement.setString(7, item.getAuthorFullName());
        statement.executeUpdate();
    }

    @Override
    public List<Review> getAll() {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from reviews"
            );
            ResultSet rs = statement.executeQuery();
            List<Review> reviews= new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(
                        (UUID) rs.getObject(1),
                        (UUID) rs.getObject(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        (UUID) rs.getObject(6),
                        rs.getString(7)

                );
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Review> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(Review item) {

    }
    public List<Review> getReviewsByPlaceId(UUID placeId){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from reviews where place_id=?"
            );
            statement.setObject(1, placeId);
            ResultSet rs = statement.executeQuery();
            List<Review> reviews= new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(
                        (UUID) rs.getObject(1),
                        (UUID) rs.getObject(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        (UUID) rs.getObject(6),
                        rs.getString(7)
                );
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public double calculateRating(UUID placeId) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("select sum(assessment) from reviews where place_id=?");
        PreparedStatement statement1 = JDBCConnection.getConn().prepareStatement("select count(assessment) from reviews where place_id=?");
        statement.setObject(1, placeId);
        statement1.setObject(1, placeId);
        ResultSet rs = statement.executeQuery();
        ResultSet rs1 = statement1.executeQuery();
        double sum = 0;
        double count = 0;
        while (rs.next()) {
            sum = rs.getInt(1);
        }
        while (rs1.next()) {
            count = rs1.getInt(1);
        }
        if (count == 0) {
            return 0;
        } else {
            return sum/count;
        }
    }
}
