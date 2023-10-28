package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.PlaceDaoImpl;
import com.example.semester_work1.models.Place;
import com.example.semester_work1.utils.FreemarkerConfigSingleton;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AddReviewServlet extends HttpServlet {
    PlaceDaoImpl placeDao;
    @Override
    public void init(){
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        placeDao = (PlaceDaoImpl) getServletContext().getAttribute("placeDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getCfg().getTemplate("add_review.ftl");
        String id = request.getParameter("placeId");
        Place place  = placeDao.getById(id).get();
        HashMap<String, Object> root = new HashMap<>();
        root.put("place", place);
        request.setAttribute("place", place);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
