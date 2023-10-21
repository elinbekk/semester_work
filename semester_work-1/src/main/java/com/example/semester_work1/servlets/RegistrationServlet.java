package com.example.semester_work1.servlets;

import com.example.semester_work1.FreemarkerConfigSingleton;
import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;
import com.example.semester_work1.services.RegistrationService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private UserDaoImpl userDao;
    private RegistrationService regService;
    @Override
    public void init() {
        userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("registration.ftl");
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
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        if(email != null || name != null || lastName != null || password != null){
            user = new User(email, name, lastName, password);
            regService.register(user);
        }
    }
}
