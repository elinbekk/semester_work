package com.example.semester_work1.dao.impl;

import com.example.semester_work1.dao.CommentToReviewDao;
import com.example.semester_work1.models.CommentToReview;
import com.example.semester_work1.utils.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentToReviewDaoImpl implements CommentToReviewDao {
    @Override
    public void save(CommentToReview item) throws SQLException {
        PreparedStatement statement = JDBCConnection.getConn().prepareStatement("insert into reviews(comment_id, review_id, author_id, comment_text, comment_date) values (?, ?, ?, ?, ?, ?)");
        statement.setString(1, item.getCommentId());
        statement.setString(2, item.getReviewId());
        statement.setString(3, item.getAuthorId());
        statement.setString(4, item.getText());
        statement.setString(5, item.getDate());
        statement.executeUpdate();
    }

    @Override
    public List<CommentToReview> getAll() {
        return null;
    }

    @Override
    public Optional<CommentToReview> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(CommentToReview item) {

    }
    public List<CommentToReview> getCommentsByReviewId(String reviewId){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from reviews where review_id=?"
            );
            statement.setString(1, reviewId);
            ResultSet rs = statement.executeQuery();
            List<CommentToReview> comments= new ArrayList<>();
            while (rs.next()) {
                CommentToReview comment = new CommentToReview(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
