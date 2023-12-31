package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import com.example.semester_work1.Helpers;
import com.example.semester_work1.models.User;
import com.example.semester_work1.services.AuthService;
//import com.example.semester_work1.services.RegistrationService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class AuthorizationServlet extends HttpServlet {
    private AuthService authService;
    private UserDaoImpl userDao;

    @Override
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        authService = (AuthService) getServletContext().getAttribute("authService");
        userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
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
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("authorization.ftl");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String message ="";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        if (email != null && password != null) {
            if (authService.isExist(email)) {
                User user = userDao.getUserByEmail(email).get();
                if (remember != null && remember.equals("on")) {
                    response.addCookie(new Cookie("user_email", email));
                }
                try {
                    if(authService.signIn(user, password, request)){
                        Helpers.redirect(response, request, "/places-list");
                    }else{
                        message = "Неверный пароль. Пожалуйста, проверьте введенные данные.";
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }else {
                message = "Пользователя с такой почтой не существует. Пожалуйста, проверьте введенные данные.";
            }
            HashMap<String, Object> root = new HashMap<>();
            root.put("message", message);
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
