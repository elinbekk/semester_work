package com.example.semester_work1.servlets;

import com.example.semester_work1.Helpers;
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
public class PlaceListServlet extends HttpServlet {
    private PlaceDaoImpl placeDao;

    @Override
    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("placeList.ftl");
        List<Place> places = placeDao.getAll();
        HashMap<String, Object> root = new HashMap<>();
        root.put("places", places);
        request.setAttribute("places", places);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
