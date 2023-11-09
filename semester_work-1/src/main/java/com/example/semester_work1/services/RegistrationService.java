package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class RegistrationService {
    UserDaoImpl userDao;
    PasswordHashService phs;

    public RegistrationService(UserDaoImpl userDao, PasswordHashService phs) {
        this.userDao = userDao;
        this.phs = phs;
    }

    public void register(User user) throws SQLException {
        Integer userID = (int) Math.random();
        user.setUserId(userID);
        try {
            user.setPassword(phs.hash(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        };
        userDao.save(user);
    }
}
