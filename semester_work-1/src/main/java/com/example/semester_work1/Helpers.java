package com.example.semester_work1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Helpers {
    public static void redirect(HttpServletResponse response, HttpServletRequest request, String path) throws IOException {
        //request.getSession().setAttribute("user", SaveClass.data);
        response.sendRedirect(request.getContextPath() + path);
    }
}
