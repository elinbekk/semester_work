package com.example.semester_work1.servlets;

import com.example.semester_work1.models.User;
import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProfileServlet extends HttpServlet {
    @Override
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("edit_profile.ftl");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
