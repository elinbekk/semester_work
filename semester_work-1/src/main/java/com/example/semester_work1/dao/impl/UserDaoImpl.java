package com.example.semester_work1.dao.impl;

import com.example.semester_work1.JDBCConnection;
import com.example.semester_work1.dao.UserDao;
import com.example.semester_work1.models.Place;
import com.example.semester_work1.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public User save(User item) throws SQLException {
        //TODO хэширование пароля, генерация id, сохранение при добавлении  фото??????????
        if(item.getUserId() == null){
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement("insert into users(user_email, user_name, user_lastname, user_password) values (?, ?, ?, ?, ?)");
            statement.setString(2, item.getEmail());
            statement.setString(3, item.getName());
            statement.setString(4, item.getLastName());
            statement.setString(5, item.getHashPassword());
        }
        return item;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password){
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from places where user_email = ? and user_password = ?"
            );
            statement.setString(1, email);
            statement.setString(2, password);
            return getUser(statement);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Optional<User> getUser(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            User user = new User(
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            );
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getById(Integer id) {
        try {
            PreparedStatement statement = JDBCConnection.getConn().prepareStatement(
                    "select * from places where place_id = ?"
            );
            statement.setLong(1, id);
            return getUser(statement);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(User item) {

    }
}
