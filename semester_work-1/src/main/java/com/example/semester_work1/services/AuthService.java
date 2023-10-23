package com.example.semester_work1.services;

import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class AuthService {
    UserDaoImpl userDao;
    PasswordHashService phs;
    public void signIn(User user, HttpServletRequest request) {
        request.getSession().setAttribute("user", user);
    }

    public boolean isNotAnon(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user == null;
    }

    public boolean isExist(User current) {
        Optional<User> ex = userDao.getUserByEmail(current.getEmail());
        return ex.isPresent();
    }

    public boolean passwordsAreMatch(User current) throws NoSuchAlgorithmException {
        User userByEmail = userDao.getUserByEmail(current.getEmail()).get();
        return phs.hash(userByEmail.getPassword()).equals(current.getPassword());
    }
}
