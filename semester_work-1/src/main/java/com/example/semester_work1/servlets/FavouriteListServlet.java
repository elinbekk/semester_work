package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.FavouritePlaceDaoImpl;
import com.example.semester_work1.dao.impl.PlaceDaoImpl;
import com.example.semester_work1.models.FavouritePlace;
import com.example.semester_work1.models.Place;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class FavouriteListServlet extends HttpServlet {
    private FavouritePlaceDaoImpl fpDao;
    private PlaceDaoImpl placeDao;

    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        this.fpDao = (FavouritePlaceDaoImpl) getServletContext().getAttribute("fpDao");
        this.placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("favourite.ftl");
        User user = (User) request.getSession(false).getAttribute("user");
        List<UUID> fpId = fpDao.getUsersAllFavourite(user.getUserId());
        List<Place> places = new ArrayList<>();
        for (int i = 0; i < fpId.size(); i++){
            Place place = placeDao.getById(fpId.get(i)).get();
            places.add(place);
        }
        HashMap<String, Object> root = new HashMap<>();
        root.put("places", places);
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
        User user = (User) request.getSession(false).getAttribute("user");
        UUID placeId = UUID.fromString(request.getParameter("placeId"));
        try {
            fpDao.delete(user.getUserId(), placeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

