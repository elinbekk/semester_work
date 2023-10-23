package com.example.semester_work1.servlets;

import com.example.semester_work1.FreemarkerConfigSingleton;
import com.example.semester_work1.Helpers;
import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.User;
import com.example.semester_work1.services.PasswordHashService;
import com.example.semester_work1.services.RegistrationService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class RegistrationServlet extends HttpServlet {
    private RegistrationService registrationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.registrationService = (RegistrationService) getServletContext().getAttribute("regService");
        //ServletContext servletContext = config.getServletContext();
        //regService = (RegistrationService) servletContext.getAttribute("regService");
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
        User user;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        if(email != null && name != null && lastName != null && password != null){
            user = new User(email, name, lastName, password);
            try {
                registrationService.register(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getSession().setAttribute("user", user);
            Helpers.redirect(response, request, "/auth");
        }
    }
}
