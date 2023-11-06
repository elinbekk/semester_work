package com.example.semester_work1.utils;

import com.example.semester_work1.dao.impl.*;
import com.example.semester_work1.services.AuthService;
import com.example.semester_work1.services.FileService;
import com.example.semester_work1.services.PasswordHashService;
import com.example.semester_work1.services.RegistrationService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PasswordHashService phs = new PasswordHashService();
        UserDaoImpl udi = new UserDaoImpl();
        sce.getServletContext().setAttribute("hashService",phs);
        sce.getServletContext().setAttribute("userDao", udi);
        sce.getServletContext().setAttribute("authService", new AuthService(udi, phs));
        sce.getServletContext().setAttribute("regService", new RegistrationService(udi, phs));
        sce.getServletContext().setAttribute("placeDao", new PlaceDaoImpl());
        sce.getServletContext().setAttribute("reviewDao", new ReviewDaoImpl());
        sce.getServletContext().setAttribute("commentDao", new CommentToReviewDaoImpl());
        sce.getServletContext().setAttribute("imageDao", new ImageDaoImpl());
        ProfilePhotoDaoImpl ppDao = new ProfilePhotoDaoImpl();
        sce.getServletContext().setAttribute("ppDao", ppDao);
        sce.getServletContext().setAttribute("fileUploadService", new FileService(ppDao));
        sce.getServletContext().setAttribute("fpDao", new FavouritePlaceDaoImpl());
    }
}
