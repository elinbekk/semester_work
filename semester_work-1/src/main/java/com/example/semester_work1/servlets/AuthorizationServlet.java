package com.example.semester_work1.servlets;

import com.example.semester_work1.FreemarkerConfigSingleton;
import com.example.semester_work1.Helpers;
import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;
import com.example.semester_work1.services.AuthService;
//import com.example.semester_work1.services.RegistrationService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    private AuthService authService;
    @Override
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        ServletContext sc = getServletContext();
        authService = (AuthService) sc.getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("authorization.ftl");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            tmpl.process(null, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        if(email != null || password != null){
            user = new User(email, password);
            if(authService.isExist(user)){
                if(remember != null && remember.equals("on")){
                    response.addCookie(new Cookie("user_email", user.getEmail()));
                }
                authService.signIn(user, request);
                request.getSession().setAttribute("user", user);
                Helpers.redirect(response, request, "/places");
            }
            else{
                Helpers.redirect(response, request, "/auth");
            }
        }
    }
}
