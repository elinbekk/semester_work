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

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public void save(Review item) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("insert into reviews(review_id, author_id, review_text, assessment, review_date, place_id) values (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, item.getReviewId());
        statement.setString(2, item.getAuthorId());
        statement.setString(3, item.getText());
        statement.setInt(4, item.getAssessment());
        statement.setString(5, item.getDate());
        statement.setInt(6, item.getPlaceId());
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
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6)
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
    public Optional<Review> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Review item) {

    }
    public List<Review> getReviewsByPlaceId(Integer placeId){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from reviews where place_id=?"
            );
            statement.setInt(1, placeId);
            ResultSet rs = statement.executeQuery();
            List<Review> reviews= new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
