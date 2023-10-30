package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.ReviewDaoImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PlaceDetailServlet extends HttpServlet {
    private PlaceDaoImpl placeDao;
    ReviewDaoImpl reviewDao;
    @Override
    public void init() {
        placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
        reviewDao = (ReviewDaoImpl) getServletContext().getAttribute("reviewDao");
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("place_detail.ftl");
        String id = request.getParameter("placeId");
        Place place  = placeDao.getById(id).get();
        List<Review> reviewList = reviewDao.getReviewsByPlaceId(Integer.valueOf(id));
        if(place == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        HashMap<String, Object> root = new HashMap<>();
        root.put("place", place);
        root.put("reviews", reviewList);
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
