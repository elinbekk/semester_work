package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.PlaceDaoImpl;
import com.example.semester_work1.models.Place;
import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AjaxSearch extends HttpServlet {
    private PlaceDaoImpl placeDao;

    @Override
    public void init() throws ServletException {
        placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String category = request.getParameter("category");
        List<Place> places;
        if (query != null && !query.isEmpty()) {
            places = placeDao.getPlaceLikeByQuery(query);
            if (category != null && !category.isEmpty()) {
                List<Place> list = new ArrayList<>();
                for (Place place : places) {
                    if (place.getCategory().equals(category)) {
                        list.add(place);
                    }
                }
                places = list;
            }
        } else if (category != null && !category.isEmpty()) {
            List<Place> list = new ArrayList<>();
            for (Place place : placeDao.getAll()) {
                if (place.getCategory().equals(category)) {
                    list.add(place);
                }
            }
            places = list;
        } else {
            places = placeDao.getAll();
        }
        JSONArray ja = new JSONArray();
        for (Place place : places) {
            ja.put(new JSONObject(place));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().write(jo.toString());
    }
}

