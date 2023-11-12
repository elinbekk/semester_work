package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class AuthService {
    UserDaoImpl userDao;
    PasswordHashService phs;
    public AuthService(UserDaoImpl userDao, PasswordHashService phs) {
        this.userDao = userDao;
        this.phs = phs;
    }

    public boolean signIn(User user, String currentInputPassword, HttpServletRequest request) throws NoSuchAlgorithmException {
        if (passwordsAreMatch(user, currentInputPassword)) {
            request.getSession().setAttribute("user", user);
            return true;
        }else {
            return false;
        }
    }
    public boolean isExist(String email) {
        Optional<User> ex = userDao.getUserByEmail(email);
        return ex.isPresent();
    }
    public boolean passwordsAreMatch(User current, String currentInputPassword) throws NoSuchAlgorithmException {
        String hashedCurrentInputPassword = phs.hash(currentInputPassword);
        return hashedCurrentInputPassword.equals(current.getPassword());
    }
}
