package com.example.semester_work1.servlets;

import com.example.semester_work1.Helpers;
import com.example.semester_work1.dao.impl.ProfilePhotoDaoImpl;
import com.example.semester_work1.dao.impl.UserDaoImpl;
import com.example.semester_work1.models.ProfilePhoto;
import com.example.semester_work1.models.User;
import com.example.semester_work1.services.FileService;
import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class EditProfileServlet extends HttpServlet {
    private UserDaoImpl userDao;
    private ProfilePhotoDaoImpl profilePhotoDao;
    private FileService fus;

    @Override
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        this.userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
        this.profilePhotoDao = (ProfilePhotoDaoImpl) getServletContext().getAttribute("ppDao");
        this.fus = (FileService) getServletContext().getAttribute("fileUploadService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        ProfilePhoto photo;
        try {
            photo = profilePhotoDao.getPhotoByUserId(user.getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("edit_profile.ftl");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("photo", photo);
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part avatar = request.getPart("avatar");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(avatar.getSubmittedFileName());
        boolean uploaded = fus.upload(user.getUserId(), avatar.getSubmittedFileName(), avatar.getSize(), avatar.getContentType(), avatar.getInputStream());
        response.getWriter().write("img/"+ avatar.getSubmittedFileName());
        if (uploaded) {
            Helpers.redirect(response, request, "/profile");
        }
    }

}
