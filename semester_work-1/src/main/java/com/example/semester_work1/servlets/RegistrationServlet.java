package com.example.semester_work1.servlets;

import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import com.example.semester_work1.Helpers;
import com.example.semester_work1.models.User;
import com.example.semester_work1.services.RegistrationService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class RegistrationServlet extends HttpServlet {
    private RegistrationService registrationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.registrationService = (RegistrationService) getServletContext().getAttribute("regService");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("registration.ftl");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String message;
        User user;
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        if (email != null && name != null && lastName != null && password != null) {
            if (!registrationService.isExist(email)){
                try {
                    user = new User(email, name, lastName, password);
                    registrationService.register(user);
                    Helpers.redirect(response, request, "/auth");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                message = "user already exists";
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
}

