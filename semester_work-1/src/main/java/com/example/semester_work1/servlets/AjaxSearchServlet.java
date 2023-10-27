package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.PlaceDaoImpl;
import com.example.semester_work1.models.Place;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AjaxSearchServlet extends HttpServlet {
    private PlaceDaoImpl placeDao;

    @Override
    public void init() throws ServletException {
        placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Place> places = placeDao.findByLikeQuery(query);

        JSONArray ja = new JSONArray();
        for (Place place: places) {
            ja.put(new JSONObject(place));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);

        response.setContentType("text/json");
        response.getWriter().write(jo.toString());

    }
}
