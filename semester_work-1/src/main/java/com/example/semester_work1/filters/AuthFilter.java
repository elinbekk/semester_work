package com.example.semester_work1.filters;

import com.example.semester_work1.Helpers;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class AuthFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        if (httpSession.getAttribute("user") == null) {
            Helpers.redirect((HttpServletResponse) response, (HttpServletRequest) request, "/auth");
        }
        chain.doFilter(request, response);
    }
}
