package com.example.semester_work1.servlets;

import com.example.semester_work1.dao.impl.CommentToReviewDaoImpl;
import com.example.semester_work1.dao.impl.ReviewDaoImpl;
import com.example.semester_work1.models.CommentToReview;
import com.example.semester_work1.models.Place;
import com.example.semester_work1.models.Review;
import com.example.semester_work1.models.User;
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
import java.text.SimpleDateFormat;
import java.util.*;

public class AddCommentServlet extends HttpServlet {
    CommentToReviewDaoImpl commentDao;
    ReviewDaoImpl reviewDao;
    @Override
    public void init() throws ServletException {
        reviewDao = (ReviewDaoImpl) getServletContext().getAttribute("reviewDao");
        commentDao = (CommentToReviewDaoImpl) getServletContext().getAttribute("commentDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("comment-text");
        User author = ((User) request.getSession().getAttribute("user"));
        String commentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        UUID reviewId = UUID.fromString(request.getParameter("reviewId"));
        CommentToReview comment = new CommentToReview(reviewId, author.getUserId(),text, commentDate, author.getName() + " " + author.getLastName());
        JSONObject jo = new JSONObject(comment);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().write(jo.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentToReview comment;
        String text = request.getParameter("comment-text");
        User author = ((User) request.getSession().getAttribute("user"));
        String commentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        UUID reviewId = UUID.fromString(request.getParameter("reviewId"));
        UUID commentId = UUID.randomUUID();
        if (text != null) {
            comment = new CommentToReview(commentId, reviewId, author.getUserId(),text, commentDate, author.getName() + " " + author.getLastName());
            try {
                commentDao.save(comment);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
