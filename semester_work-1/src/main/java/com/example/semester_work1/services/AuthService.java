package com.example.semester_work1.services;

import com.example.semester_work1.models.User;

import javax.servlet.http.HttpServletRequest;

public class AuthService {
    //TODO проверка на запомнить меня(cookie)
    public void signIn(User user, HttpServletRequest request){
        request.getSession().setAttribute("user", user);
    }
    public boolean isNotAnon(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user == null;
    }
}
