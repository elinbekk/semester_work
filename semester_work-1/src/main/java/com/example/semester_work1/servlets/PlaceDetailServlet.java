package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.ImageDaoImpl;
import com.example.semester_work1.dao.impl.ReviewDaoImpl;
import com.example.semester_work1.models.Image;
import com.example.semester_work1.models.Review;
import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import com.example.semester_work1.dao.impl.PlaceDaoImpl;
import com.example.semester_work1.models.Place;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PlaceDetailServlet extends HttpServlet {
    private PlaceDaoImpl placeDao;
    private ReviewDaoImpl reviewDao;
    private ImageDaoImpl imageDao;
    @Override
    public void init() {
        placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
        reviewDao = (ReviewDaoImpl) getServletContext().getAttribute("reviewDao");
        imageDao = (ImageDaoImpl) getServletContext().getAttribute("imageDao");
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("place_detail.ftl");
        String id = request.getParameter("placeId");
        Place place  = placeDao.getById(id).get();
        try {
            place.setRating(reviewDao.calculateRating(Integer.valueOf(place.getPlaceId())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Review> reviewList = reviewDao.getReviewsByPlaceId(Integer.valueOf(id));
        List<Image> imageList = imageDao.getImagesByPlaceId(Integer.valueOf(id));
        HashMap<String, Object> root = new HashMap<>();
        root.put("place", place);
        root.put("reviews", reviewList);
        root.put("images", imageList);
        request.setAttribute("place", place);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
