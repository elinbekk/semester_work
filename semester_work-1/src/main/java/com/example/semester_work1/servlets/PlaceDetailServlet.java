package com.example.semester_work1.servlets;

import com.example.semester_work1.FreemarkerConfigSingleton;
import com.example.semester_work1.dao.impl.PlaceDaoImpl;
import com.example.semester_work1.models.Place;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PlaceDetailServlet extends HttpServlet {
    private PlaceDaoImpl placeDao;

    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("placeId");
        if(id == null){

        }
        Optional<Place> place  = placeDao.getById(Integer.parseInt(id));
        if(!place.isPresent()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        request.setAttribute("place", place);
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("place_detail.ftl");
        response.setContentType("text/html");
        try {
            tmpl.process(null, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
