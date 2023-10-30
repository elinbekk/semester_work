package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class AuthService {
    UserDaoImpl userDao;

    public void signIn(User user, HttpServletRequest request) throws NoSuchAlgorithmException {
        if (passwordsAreMatch(user)) {
            request.getSession().setAttribute("user", user);
        }
    }
    public AuthService(UserDaoImpl userDao, PasswordHashService phs) {
        this.userDao = userDao;
    }
    public boolean isExist(String email) {
        Optional<User> ex = userDao.getUserByEmail(email);
        return ex.isPresent();
    }
    public boolean passwordsAreMatch(User current) throws NoSuchAlgorithmException {
        User userByEmail = userDao.getUserByEmail(current.getEmail()).get();
        String hashedPassword = current.getPassword();
        return hashedPassword.equals(userByEmail.getPassword());
    }
}
