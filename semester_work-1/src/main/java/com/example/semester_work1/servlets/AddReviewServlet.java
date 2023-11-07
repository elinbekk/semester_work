package com.example.semester_work1.servlets;

import com.example.semester_work1.Helpers;
import com.example.semester_work1.dao.impl.PlaceDaoImpl;
import com.example.semester_work1.dao.impl.ReviewDaoImpl;
import com.example.semester_work1.models.Place;
import com.example.semester_work1.models.Review;
import com.example.semester_work1.models.User;
import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddReviewServlet extends HttpServlet {
    PlaceDaoImpl placeDao;
    ReviewDaoImpl reviewDao;
    @Override
    public void init(){
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
        reviewDao = (ReviewDaoImpl) getServletContext().getAttribute("reviewDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("add_review.ftl");
        String id = request.getParameter("placeId");
        //TODO исправить тип айди
        Place place  = placeDao.getById(id).get();
        List<Review> reviewsList = reviewDao.getReviewsByPlaceId(Integer.valueOf(id));
        HashMap<String, Object> root = new HashMap<>();
        root.put("place", place);
        root.put("reviews", reviewsList);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Review review;
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("review-text");
        int assessment = Integer.parseInt(request.getParameter("assessment"));
        User author = ((User) request.getSession().getAttribute("user"));
        String reviewDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        int placeId = Integer.parseInt(request.getParameter("placeId"));
        if (text != null && request.getParameter("assessment") != null) {
            review = new Review(1, author.getUserId(), text, assessment, reviewDate, placeId, author.getName() + " " + author.getLastName());
            try {
                reviewDao.save(review);
                Helpers.redirect(response, request, "/places/detail?placeId=" + placeId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
