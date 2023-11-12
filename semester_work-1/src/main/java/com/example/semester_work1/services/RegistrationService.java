package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class RegistrationService {
    UserDaoImpl userDao;
    PasswordHashService phs;

    public RegistrationService(UserDaoImpl userDao, PasswordHashService phs) {
        this.userDao = userDao;
        this.phs = phs;
    }

    public boolean isExist(String email) {
        Optional<User> ex = userDao.getUserByEmail(email);
        return ex.isPresent();
    }

    public void register(User user) throws SQLException {
        user.setUserId(UUID.randomUUID());
        try {
            user.setPassword(phs.hash(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        userDao.save(user);
    }
}
