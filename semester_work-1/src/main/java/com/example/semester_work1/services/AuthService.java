package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class AuthService {
    UserDaoImpl userDao;
    PasswordHashService phs;

    public void signIn(User user, HttpServletRequest request) throws NoSuchAlgorithmException {
        if (passwordsAreMatch(user)) {
            request.getSession().setAttribute("user", user);
        }

    }

    public AuthService(UserDaoImpl userDao, PasswordHashService phs) {
        this.userDao = userDao;
        this.phs = phs;
    }

    public boolean isExist(String email) {
        Optional<User> ex = userDao.getUserByEmail(email);
        return ex.isPresent();
    }

    public boolean passwordsAreMatch(User current) throws NoSuchAlgorithmException {
        User userByEmail = userDao.getUserByEmail(current.getEmail()).get();
        return phs.hash(userByEmail.getPassword()).equals(current.getPassword());
    }
}
